package com.vodafone.conference.api.controllers;

import java.util.*;
import java.util.stream.Collectors;

import com.vodafone.conference.api.mapper.ParticipantMapper;
import com.vodafone.conference.api.repositories.ParticipantRepository;
import com.vodafone.conference.models.entities.DTO.ParticipantCreationDTO;
import com.vodafone.conference.models.entities.DTO.ParticipantDTO;
import com.vodafone.conference.services.ParticipantService;
import org.apache.catalina.mapper.Mapper;
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
public class ParticipantsController {

    @Autowired
    private ParticipantService participantService;
    private ParticipantMapper mapper;

    public ParticipantsController(ParticipantService participantService, ParticipantMapper mapper) {

        this.participantService = participantService;
        this.mapper = mapper;

    }

    // get a participant by id
    // DOES NOT WORK FOR CERTAIN UUIDs (UUID string too large)
    @GetMapping("participants/{participant-id}")
    public ResponseEntity<ParticipantDTO> getParticipantById(@PathVariable("participant-id") String id) {
        Optional<Participant> optParticipant = participantService.findById(UUID.fromString(id));
        if(optParticipant.isPresent()) {
            return new ResponseEntity<>(mapper.toDto(optParticipant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // get all the participants belonging to a certain session
    // participant should not have session ID
    // as sessions added/created by participants are unique to them (i.e. two participants cannot add/create the same session)
    /*@GetMapping("sessions/{session-id}/participants")
    public ResponseEntity<List<ParticipantDTO>> getSessionParticipantsBySessionId(@PathVariable("session-id") String id) {

        List<ParticipantDTO> participants = participantService.findBySessions_Id(UUID.fromString(id)).stream()
                .map(participant -> mapper.toDto(participant)).collect(Collectors.toList());

        return new ResponseEntity<>(participants, HttpStatus.OK);
        //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }*/

    // get all the participants belonging to a certain conference
    // check
    @GetMapping("conferences/{conference-id}/participants")
    public ResponseEntity<List<ParticipantDTO>> getConferenceParticipantsByConferenceId(@PathVariable("conference-id") String id) {

        List<ParticipantDTO> participants = participantService.findByConference_Id(UUID.fromString(id)).stream()
                .map(participant -> mapper.toDto(participant)).collect(Collectors.toList());

        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    // check
    @PostMapping(path= "conferences/{conference-id}/participants", consumes = "application/json")
    public ResponseEntity<ParticipantDTO> createParticipant(@Valid @RequestBody ParticipantCreationDTO participantCreationDTO, @PathVariable("conference-id") String conferenceId, Errors errors) {

        Participant participant = mapper.toParticipant(participantCreationDTO);
        ParticipantDTO participantDTO = mapper.toDto(participant);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        participantService.save(participant, UUID.fromString(conferenceId));
        return new ResponseEntity<>(participantDTO, HttpStatus.CREATED);
    }

    //rewrite a participant by id
    // check
    @PutMapping("/participants/{participant-id}")
    public ResponseEntity<ParticipantDTO> putParticipant(@Valid @RequestBody ParticipantCreationDTO participantCreationDTO, Errors errors, @PathVariable("participant-id") String id) {

        Participant participant = mapper.toParticipant(participantCreationDTO);
        ParticipantDTO participantDTO = mapper.toDto(mapper.toParticipant(participantCreationDTO));
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        participantService.update(participant, UUID.fromString(id));
        return new ResponseEntity<>(participantDTO, HttpStatus.OK);
    }

    // update a participant by id
    // implement validation check
    // check
    @PatchMapping("participants/{participant-id}")
    public ResponseEntity<ParticipantDTO> patchParticipant(@PathVariable("participant-id") String id, @Valid @RequestBody ParticipantCreationDTO participantCreationDTO ) {
        Participant participant = participantService.findById(UUID.fromString(id)).get();
        if (participantCreationDTO.getFirstName() != null) {
            participant.setFirstName(participantCreationDTO.getFirstName());
        }
        if (participantCreationDTO.getLastName() != null) {
            participant.setLastName(participantCreationDTO.getLastName());
        }
        if (participantCreationDTO.getTitle() != null) {
            participant.setTitle(participantCreationDTO.getTitle());
        }
        if (participantCreationDTO.getEmail() != null) {
            participant.setEmail(participantCreationDTO.getEmail());
        }
        if (participantCreationDTO.getPhoneNumber() != null) {
            participant.setPhoneNumber(participantCreationDTO.getPhoneNumber());
        }
        if (participantCreationDTO.getUsername() != null) {
            participant.setUsername(participantCreationDTO.getUsername());
        }
        if (participantCreationDTO.getPassword() != null) {
            participant.setPassword(participantCreationDTO.getPassword());
        }

        participantService.update(participant, UUID.fromString(id));
        return new ResponseEntity<>(mapper.toDto(participant), HttpStatus.OK);
    }

    // delete a participant by id
    // DELETE method may be implemented with ResponseEntity
    // handle exception
    // check
    @DeleteMapping("participants/{participant-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteParticipant (@PathVariable("participant-id") String id) {
        try {
            participantService.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {}

    }


}
