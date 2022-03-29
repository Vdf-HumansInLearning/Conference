package com.vodafone.conference.api.controllers;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vodafone.conference.api.mapper.SpeakerMapper;
import com.vodafone.conference.api.repositories.SpeakerRepository;
import com.vodafone.conference.models.entities.DTO.ParticipantDTO;
import com.vodafone.conference.models.entities.DTO.SpeakerCreationDTO;
import com.vodafone.conference.models.entities.DTO.SpeakerDTO;
import com.vodafone.conference.models.entities.Speaker;
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
// base path URL will break requests
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class SpeakersController {

    @Autowired
    //private SpeakerRepository speakersRepo;
    private SpeakerService speakerService;
    private SpeakerMapper mapper;

    public  SpeakersController(SpeakerService speakerService, SpeakerMapper speakerMapper) {
        this.speakerService = speakerService;
        this.mapper = speakerMapper;
    }

    // get speaker by id
    // implementation fixed
    @GetMapping("speakers/{speaker-id}")
    public ResponseEntity<SpeakerDTO> getSpeakerById(@PathVariable("speaker-id") String id) {
        Optional<Speaker> optSpeaker = speakerService.findById(UUID.fromString(id));
        if(optSpeaker.isPresent()) {
            //SpeakerDTO speakerDTO = mapper.toDto(optSpeaker.get());
            return new ResponseEntity<>(mapper.toDto(optSpeaker.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // get all speakers belonging to a session
    // implementation fixed
    @GetMapping("sessions/{session-id}/speakers")
    public ResponseEntity<List<SpeakerDTO>> getSessionSpeakersBySessionId(@PathVariable("session-id") String id) {

        List<SpeakerDTO> speakers = speakerService.findBySessions_Id(UUID.fromString(id)).stream()
                .map(speaker -> mapper.toDto(speaker)).collect(Collectors.toList());

        return new ResponseEntity<>(speakers, HttpStatus.OK);
    }

    // get all speakers belonging to a conference
    // implementation done
    @GetMapping("conferences/{conference-id}/speakers")
    public ResponseEntity<List<SpeakerDTO>> getConferenceSpeakersByConferenceId(@PathVariable("conference-id") String id) {

        List<SpeakerDTO> speakers = speakerService.findByConference_Id(UUID.fromString(id)).stream()
                .map(speaker -> mapper.toDto(speaker)).collect(Collectors.toList());

        return new ResponseEntity<>(speakers, HttpStatus.OK);
    }

    //create a speaker (must include conference and session id)
    @PostMapping(path= "conferences/{conference-id}/sessions/{session-id}", consumes = "application/json")
    public ResponseEntity<SpeakerDTO> createSpeaker(@Valid @RequestBody SpeakerCreationDTO speakerCreationDTO, Errors errors) {

        Speaker speaker = mapper.toSpeaker(speakerCreationDTO);
        SpeakerDTO speakerDTO = mapper.toDto(speaker);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        speakerService.save(speaker);
        return new ResponseEntity<>(speakerDTO, HttpStatus.CREATED);
    }

    // rewrite a speaker by id
    @PutMapping("speakers/{speaker-id}")
    public ResponseEntity<SpeakerDTO> putSpeaker(@Valid @RequestBody SpeakerCreationDTO speakerCreationDTO, Errors errors, @PathVariable("speaker-id") String id)
    {

        Speaker speaker = mapper.toSpeaker(speakerCreationDTO);
        SpeakerDTO speakerDTO = mapper.toDto(speaker);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        speakerService.update(speaker, UUID.fromString(id));
        return new ResponseEntity<>(speakerDTO, HttpStatus.OK);
    }

    // update a speaker by id
    // TO DO PATCH method
    // implement validation check
    @PatchMapping("speakers/{speaker-id}")
    public ResponseEntity<SpeakerDTO> patchSpeaker(@PathVariable("speaker-id") String id, @Valid @RequestBody SpeakerCreationDTO speakerCreationDTO) {
        Speaker speaker = speakerService.findById(UUID.fromString(id)).get();

        //check inner participant fields
        if (speakerCreationDTO.getParticipant().getFirstName() != null) {
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
        }

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
        return new ResponseEntity<>(mapper.toDto(speaker), HttpStatus.OK);
    }

    // delete a speaker by id
    // DELETE method may be implemented with ResponseEntity
    // handle exception
    // implementation done
    @DeleteMapping("speakers/{speaker-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSpeaker (@PathVariable("speaker-id") String id) {
        try {
            speakerService.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {}

    }
}
