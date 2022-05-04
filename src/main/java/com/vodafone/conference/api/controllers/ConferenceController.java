package com.vodafone.conference.api.controllers;

import com.vodafone.conference.api.mapper.ConferenceMapper;
import com.vodafone.conference.exceptions.ApiRequestException;
import com.vodafone.conference.models.dto.ConferenceCreationDTO;
import com.vodafone.conference.models.dto.ConferenceDTO;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.services.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ConferenceMapper conferenceMapper;

    @GetMapping(value = "/conferences", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConferenceDTO>> getAllConferences() {
        if (conferenceService.findAll().size() == 0) {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.CONFERENCE_NOT_FOUND, ""));
        }

        List<ConferenceDTO> conferenceDTOList = conferenceService.findAll().stream().map(conferenceMapper::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(conferenceDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/conferences/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceDTO> getConferenceById(@PathVariable UUID id) {
        Optional<Conference> conferenceOptional = conferenceService.findById(id);

        return conferenceOptional.map(conference -> new ResponseEntity<>(conferenceMapper.toDto(conference), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/conferences", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceDTO> createConference(@RequestBody ConferenceCreationDTO conferenceCreationDTO, Errors errors) {
        Conference conference = conferenceMapper.toConference(conferenceCreationDTO);
        ConferenceDTO conferenceDTO = conferenceMapper.toDto(conference);

        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        conferenceService.save(conference);
        return new ResponseEntity<>(conferenceDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "conferences/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConferenceDTO> updateConferenceById(@PathVariable(name = "id") UUID id, @Valid @RequestBody ConferenceCreationDTO conferenceCreationDTO, Errors errors){
        Conference conference = conferenceMapper.toConference(conferenceCreationDTO);
        ConferenceDTO conferenceDTO = conferenceMapper.toDto(conference);

        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        conferenceService.update(conference, id);

        return new ResponseEntity<>(conferenceDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/conferences/{id}")
    public ResponseEntity deleteById(@PathVariable(name = "id") UUID id) throws Exception {
        try {
            conferenceService.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new Exception(e);
        }
    }
}
