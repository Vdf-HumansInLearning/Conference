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
// base path URL will break requests
@RequestMapping(path="/participants", produces = "application/json")
@CrossOrigin(origins = "*")
public class ParticipantsController {

    @Autowired
    //private ParticipantRepository participantsRepo;
    private ParticipantService participantService;
    private ParticipantMapper mapper;

    public ParticipantsController(ParticipantService participantService, ParticipantMapper mapper) {

        this.participantService = participantService;
        this.mapper = mapper;

    }

    // May want to rework endpoint for returning participants belonging to a conference and to a session

    // get a participant by id
    // implementation fixed
    @GetMapping("{participant-id}")
    public ResponseEntity<ParticipantDTO> getParticipantById(@PathVariable("participant-id") UUID id) {
        Optional<Participant> optParticipant = participantService.findById(id);
        if(optParticipant.isPresent()) {
            //ParticipantDTO participantDTO = mapper.toDto(optParticipant.get());
            return new ResponseEntity<>(mapper.toDto(optParticipant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // get all the participants belonging to a certain session
    // implementation fixed
    @GetMapping("sessions/{session-id}")
    public ResponseEntity<List<ParticipantDTO>> getSessionParticipantsBySessionId(@PathVariable("session-id") UUID id) {

        List<ParticipantDTO> participants = participantService.findBySessions_Id(id).stream()
                .map(participant -> mapper.toDto(participant)).collect(Collectors.toList());

        return new ResponseEntity<>(participants, HttpStatus.OK);
        //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // get all the participants belonging to a certain conference
    //implementation fixed
    @GetMapping("conferences/{conference-id}")
    public ResponseEntity<List<ParticipantDTO>> getConferenceParticipantsByConferenceId(@PathVariable("conference-id") UUID id) {

        List<ParticipantDTO> participants = participantService.findByConference_Id(id).stream()
                .map(participant -> mapper.toDto(participant)).collect(Collectors.toList());

        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    //create a participant (must include conference and session id)
    //@PostMapping(consumes = "application/json")
    @PostMapping(path= "{conference-id}/{session-id}", consumes = "application/json")
    public ResponseEntity<ParticipantDTO> createParticipant(@Valid @RequestBody ParticipantCreationDTO participantCreationDTO, Errors errors) {

        Participant participant = mapper.toParticipant(participantCreationDTO);
        ParticipantDTO participantDTO = mapper.toDto(participant);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        participantService.save(participant);
        return new ResponseEntity<>(participantDTO, HttpStatus.CREATED);
    }

    //rewrite a participant by id
    @PutMapping("{participant-id}")
    public ResponseEntity<ParticipantDTO> putParticipant(@Valid @RequestBody ParticipantCreationDTO participantCreationDTO, Errors errors, @PathVariable("participant-id") String parameter) {

        Participant participant = mapper.toParticipant(participantCreationDTO);
        ParticipantDTO participantDTO = mapper.toDto(mapper.toParticipant(participantCreationDTO));
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        participantService.save(participant);
        return new ResponseEntity<>(participantDTO, HttpStatus.OK);
    }

    // update a participant by id
    // implement validation check
    @PatchMapping("{participant-id}")
    public ResponseEntity<ParticipantDTO> patchParticipant(@PathVariable("participant-id") UUID id, @Valid @RequestBody ParticipantCreationDTO patch ) {
        Participant participant = participantService.findById(id).get();
        if (patch.getFirstName() != null) {
            participant.setFirstName(patch.getFirstName());
        }
        if (patch.getLastName() != null) {
            participant.setLastName(patch.getLastName());
        }
        if (patch.getTitle() != null) {
            participant.setTitle(patch.getTitle());
        }
        if (patch.getEmail() != null) {
            participant.setEmail(patch.getEmail());
        }
        if (patch.getPhoneNumber() != null) {
            participant.setPhoneNumber(patch.getPhoneNumber());
        }
        if (patch.getUsername() != null) {
            participant.setUsername(patch.getUsername());
        }
        if (patch.getPassword() != null) {
            participant.setPassword(patch.getPassword());
        }

        participantService.save(participant);
        return new ResponseEntity<>(mapper.toDto(participant), HttpStatus.OK);
    }

    // delete a participant by id
    // DELETE method may be implemented with ResponseEntity
    // handle exception
    // implementation done
    @DeleteMapping("{participant-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteParticipant (@PathVariable("participant-id") String id) {
        try {
            participantService.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {}

    }


}
