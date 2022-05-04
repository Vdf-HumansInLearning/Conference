package com.vodafone.conference.api.controllers;

import com.vodafone.conference.api.mapper.SessionMapper;
import com.vodafone.conference.api.mapper.SessionTypeMapper;
import com.vodafone.conference.exceptions.ApiRequestException;
import com.vodafone.conference.models.dto.SessionCreationDTO;
import com.vodafone.conference.models.dto.SessionDTO;
import com.vodafone.conference.models.dto.SessionTypeDTO;
import com.vodafone.conference.models.entities.*;
import com.vodafone.conference.services.SessionService;
import com.vodafone.conference.services.SessionTypeService;
import com.vodafone.conference.services.SpeakerService;
import com.vodafone.conference.services.TrackService;
import com.vodafone.conference.services.utils.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = "application/json")
public class SessionController {

    private final SessionService sessionService;
    private final SessionTypeService sessionTypeService;
    private final TrackService trackService;
    private final SessionMapper sessionMapper;
    private final SessionTypeMapper sessionTypeMapper;
    private final SpeakerService speakerService;

    @Autowired
    private EmailSenderService senderService;

    public SessionController(SessionService sessionService, SessionTypeService sessionTypeService, TrackService trackService, SessionMapper sessionMapper, SessionTypeMapper sessionTypeMapper, SpeakerService speakerService) {
        this.sessionService = sessionService;
        this.sessionTypeService = sessionTypeService;
        this.trackService = trackService;
        this.sessionMapper = sessionMapper;
        this.sessionTypeMapper = sessionTypeMapper;
        this.speakerService = speakerService;
    }

    @PostMapping(path = "tracks/{track-id}/sessions", consumes = "application/json")
    public ResponseEntity<SessionDTO> createSession(@Valid @RequestBody SessionCreationDTO sessionCreationDTO, @PathVariable("track-id") String trackId, Errors errors) {

        Track track = trackService.findById(UUID.fromString(trackId));
        if (track == null) {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, trackId));
        }
        if (errors.hasFieldErrors()) {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.BAD_INPUT));
        } else {
            SessionType sessionType = sessionTypeMapper.toSessionType(sessionCreationDTO.getSessionTypeCreationDTO());
            sessionTypeService.save(sessionType);

            Session session = sessionMapper.toSession(sessionCreationDTO);
            session.setSessionType(sessionType);
            session.setTrack(track);
            SessionDTO sessionDTO = sessionMapper.toDto(session);
            sessionDTO.setSessionTypeDTO(sessionTypeMapper.toDto(sessionType));

            sessionService.save(session);
            return new ResponseEntity<>(sessionDTO, HttpStatus.CREATED);
        }
    }

    @GetMapping("sessions")
    public ResponseEntity<List<SessionDTO>> getSessions() {
        List<SessionDTO> sessionDTOList = sessionService.findAll().stream().map(sessionMapper::toDto).collect(Collectors.toList());
        List<Session> sessionList = sessionService.findAll();

        SessionDTO[] sessionDTOArray = sessionDTOList.toArray(new SessionDTO[0]);
        SessionTypeDTO[] sessionTypeDTOArray = sessionList.stream().map(session -> sessionTypeMapper.toDto(session.getSessionType())).toArray(SessionTypeDTO[]::new);

        for (int i = 0; i < sessionDTOList.size(); i++) {
            sessionDTOArray[i].setSessionTypeDTO(sessionTypeDTOArray[i]);
        }
        sessionDTOList = Arrays.asList(sessionDTOArray);
        return new ResponseEntity<>(sessionDTOList, HttpStatus.OK);
    }

    @GetMapping("sessions/{session-id}")
    public ResponseEntity<SessionDTO> getSessionById(@PathVariable("session-id") String id) {
        Optional<Session> optSession = sessionService.findById(UUID.fromString(id));
        if (optSession.isPresent()) {
            Session session = optSession.get();
            SessionTypeDTO sessionTypeDTO = sessionTypeMapper.toDto(session.getSessionType());
            SessionDTO sessionDTO = sessionMapper.toDto(session);
            sessionDTO.setSessionTypeDTO(sessionTypeDTO);
            return new ResponseEntity<>(sessionDTO, HttpStatus.OK);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id));
        }
    }

    @PutMapping("sessions/{session-id}")
    public ResponseEntity<SessionDTO> putSessionById(@Valid @RequestBody SessionCreationDTO sessionCreationDTO, Errors errors, @PathVariable("session-id") String id) {
        Session session = sessionMapper.toSession(sessionCreationDTO);
        SessionDTO sessionDTO = sessionMapper.toDto(session);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sessionService.update(session, UUID.fromString(id));

        sessionDTO.getParticipants().forEach(participant -> senderService.sendEmail(participant.getEmail(), "Test", "test"));
//        senderService.sendEmail("ichim_sorin98@yahoo.com", "Test subject", "Test Body");

        return new ResponseEntity<>(sessionDTO, HttpStatus.OK);
    }

    @PostMapping("speakers/{speaker-id}/sessions/{session-id}")
    public ResponseEntity<SessionDTO> addSpeakerToSession(@PathVariable("speaker-id") String speakerId,
                                                          @PathVariable("session-id") String sessionId) {

        Optional<Speaker> speaker = speakerService.findById(UUID.fromString(speakerId));
        Optional<Session> session = sessionService.findById(UUID.fromString(sessionId));

        if (session.isPresent() && speaker.isPresent()) {
            session.get().addSpeaker(speaker.get());
            sessionService.save(session.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("sessions/{session-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSession(@PathVariable("session-id") String id) throws Exception {
        try {
            sessionService.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {
            throw new Exception(e);
        }
    }

}
