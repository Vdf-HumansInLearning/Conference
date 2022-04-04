package com.vodafone.conference.api.controllers;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vodafone.conference.api.mapper.ParticipantMapper;
import com.vodafone.conference.api.mapper.SpeakerMapper;
import com.vodafone.conference.api.repositories.SpeakerRepository;
import com.vodafone.conference.models.entities.DTO.ParticipantDTO;
import com.vodafone.conference.models.entities.DTO.SpeakerCreationDTO;
import com.vodafone.conference.models.entities.DTO.SpeakerDTO;
import com.vodafone.conference.models.entities.Speaker;
import com.vodafone.conference.services.ParticipantService;
import com.vodafone.conference.services.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

//import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.vodafone.conference.models.entities.Participant;

import javax.validation.Valid;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class SpeakersController {

    @Autowired
    //private SpeakerRepository speakersRepo;
    private SpeakerService speakerService;
    private ParticipantService participantService;
    private SpeakerMapper speakerMapper;
    private ParticipantMapper participantMapper;

    public SpeakersController(SpeakerService speakerService, ParticipantService participantService, SpeakerMapper speakerMapper, ParticipantMapper participantMapper) {
        this.speakerService = speakerService;
        this.participantService = participantService;
        this.speakerMapper = speakerMapper;
        this.participantMapper = participantMapper;
    }

    // get speaker by id
    // implementation fixed
    // check
    @GetMapping("speakers/{speaker-id}")
    public ResponseEntity<SpeakerDTO> getSpeakerById(@PathVariable("speaker-id") String id) {
        Optional<Speaker> optSpeaker = speakerService.findById(UUID.fromString(id));

        Speaker speaker = optSpeaker.get();
        ParticipantDTO participantDTO = participantMapper.toDto(speaker.getParticipant());
        SpeakerDTO speakerDTO = speakerMapper.toDto(speaker);
        speakerDTO.setParticipantDTO(participantDTO);

        if(optSpeaker.isPresent()) {
            return new ResponseEntity<>(speakerDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // get all speakers belonging to a session
    // implementation fixed
    /*@GetMapping("sessions/{session-id}/speakers")
    public ResponseEntity<List<SpeakerDTO>> getSessionSpeakersBySessionId(@PathVariable("session-id") String id) {

        List<SpeakerDTO> speakers = speakerService.findBySessions_Id(UUID.fromString(id)).stream()
                .map(speaker -> speakerMapper.toDto(speaker)).collect(Collectors.toList());

        return new ResponseEntity<>(speakers, HttpStatus.OK);
    }*/

    // get all speakers belonging to a conference
    // check
    @GetMapping("conferences/{conference-id}/speakers")
    public ResponseEntity<List<SpeakerDTO>> getConferenceSpeakersByConferenceId(@PathVariable("conference-id") String id) {

        List<Speaker> speakers = speakerService.findByConference_Id(UUID.fromString(id));
        List<ParticipantDTO> participantDTOS = speakers.stream().map(speaker -> participantMapper.toDto(speaker.getParticipant())).collect(Collectors.toList());
        List<SpeakerDTO> speakerDTOS = speakerService.findByConference_Id(UUID.fromString(id)).stream()
                .map(speaker -> speakerMapper.toDto(speaker)).collect(Collectors.toList());

        // writing participantDTOs could be done declaratively
        SpeakerDTO[] speakerDTOarray = speakerDTOS.toArray(new SpeakerDTO[speakerDTOS.size()]);
        ParticipantDTO[] participantDTOarray = participantDTOS.toArray(new ParticipantDTO[participantDTOS.size()]);

        for (int i = 0; i < speakerDTOS.size(); i++) {
            speakerDTOarray[i].setParticipantDTO(participantDTOarray[i]);
        }

        speakerDTOS = Arrays.asList(speakerDTOarray);

        return new ResponseEntity<>(speakerDTOS, HttpStatus.OK);
    }

    //create a speaker (must include conference and session id)
    // check
    @PostMapping(path= "conferences/{conference-id}/speakers", consumes = "application/json")
    public ResponseEntity<SpeakerDTO> createSpeaker(@Valid @RequestBody SpeakerCreationDTO speakerCreationDTO, @PathVariable("conference-id") String conferenceId, Errors errors) {

        // create speaker entity and save fields coming from creation DTO
        Speaker speaker = speakerMapper.toSpeaker(speakerCreationDTO);

        // create participant entity from participantCreationDTO coming from Front-End
        Participant participant = participantMapper.toParticipant(speakerCreationDTO.getParticipantCreationDTO());
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // save participant first
        participantService.save(participant, UUID.fromString(conferenceId));

        // set participantId for speaker entity
        speaker.setParticipant(participant);

        // set speaker DTO and response
        SpeakerDTO speakerDTO = speakerMapper.toDto(speaker);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        speakerService.save(speaker, UUID.fromString(conferenceId), speaker.getParticipant().getId());
        return new ResponseEntity<>(speakerDTO, HttpStatus.CREATED);
    }

    // rewrite a speaker by id
    // check
    @PutMapping("speakers/{speaker-id}")
    public ResponseEntity<SpeakerDTO> putSpeaker(@Valid @RequestBody SpeakerCreationDTO speakerCreationDTO, Errors errors, @PathVariable("speaker-id") String id)
    {

        //PUT request body may not include a participant entity
        // the associated participant entity may be updated by PUT request to Participant entity from Front-End

        Speaker speaker = speakerMapper.toSpeaker(speakerCreationDTO);
        SpeakerDTO speakerDTO = speakerMapper.toDto(speaker);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        speakerService.update(speaker, UUID.fromString(id));
        return new ResponseEntity<>(speakerDTO, HttpStatus.OK);
    }

    // update a speaker by id
    // check
    @PatchMapping("speakers/{speaker-id}")
    public ResponseEntity<SpeakerDTO> patchSpeaker(@PathVariable("speaker-id") String id, @Valid @RequestBody SpeakerCreationDTO speakerCreationDTO) {
        Speaker speaker = speakerService.findById(UUID.fromString(id)).get();


        // PATCH request body may not include updates to the fields of the associated Participant entity
        // the associated participant entity may be updated by PUT request to Participant entity from Front-End

        //these fields belong to participant entity in DB and may be changed by PUT request to participant
        /*if (speakerCreationDTO.getParticipant().getFirstName() != null) {
            speaker.getParticipant().setFirstName(speakerCreationDTO.getParticipant().getFirstName());
        }
        if (speakerCreationDTO.getParticipant().getLastName() != null) {
            speaker.getParticipant().setLastName(speakerCreationDTO.getParticipant().getLastName());
        }
        if (speakerCreationDTO.getTitle() != null) {
            speaker.getParticipant().setTitle(speakerCreationDTO.getTitle());
        }
        if (speakerCreationDTO.getParticipant().getEmail() != null) {
            speaker.getParticipant().setEmail(speakerCreationDTO.getParticipant().getEmail());
        }
        if (speakerCreationDTO.getParticipant().getPhoneNumber() != null) {
            speaker.getParticipant().setPhoneNumber(speakerCreationDTO.getParticipant().getPhoneNumber());
        }
        if (speakerCreationDTO.getParticipant().getUsername() != null) {
            speaker.getParticipant().setUsername(speakerCreationDTO.getParticipant().getUsername());
        }
        if (speakerCreationDTO.getParticipant().getPassword() != null) {
            speaker.getParticipant().setPassword(speakerCreationDTO.getParticipant().getPassword());
        }*/

        // check speaker specific fields
        if (speakerCreationDTO.getCompany() != null) {
            speaker.setCompany(speakerCreationDTO.getCompany());
        }
        if (speakerCreationDTO.getLinkedinAcc() != null) {
            speaker.setLinkedinAcc(speakerCreationDTO.getLinkedinAcc());
        }
        if (speakerCreationDTO.getTwitterAcc() != null) {
            speaker.setTwitterAcc(speakerCreationDTO.getTwitterAcc());
        }
        if (speakerCreationDTO.getGithubAcc() != null) {
            speaker.setGithubAcc(speakerCreationDTO.getGithubAcc());
        }
        if (speakerCreationDTO.getBiography() != null) {
            speaker.setBiography(speakerCreationDTO.getBiography());
        }

        speakerService.update(speaker, UUID.fromString(id));
        return new ResponseEntity<>(speakerMapper.toDto(speaker), HttpStatus.OK);
    }

    // delete a speaker by id
    // DELETE method may be implemented with ResponseEntity
    // handle exception
    // check
    @DeleteMapping("speakers/{speaker-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSpeaker (@PathVariable("speaker-id") String id) {
        try {
            speakerService.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {}

    }
}
