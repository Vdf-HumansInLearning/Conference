package com.vodafone.conference.api.controllers;

import java.util.*;

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

    //@Autowired
    //EntityLinks entityLinks;

    public ParticipantsController(ParticipantService participantService, ParticipantMapper mapper) {

        this.participantService = participantService;
        this.mapper = mapper;

    }

    // May want to rework endpoint for returning participants belonging to a conference and to a session

    @GetMapping("conferences/{conference-id}/participants/{participant-id}")
    public ResponseEntity<Participant> conferenceParticipantById(@PathVariable("participant-id") UUID id) {
        Optional<Participant> optParticipant = participantService.findById(id);
        if(optParticipant.isPresent()) {
            return new ResponseEntity<>(optParticipant.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("tracks/{track-id}/sessions/{session-id}/participants/{participant-id}")
    public ResponseEntity<Participant> sessionParticipantById(@PathVariable("participant-id") UUID id) {
        Optional<Participant> optParticipant = participantService.findById(id);
        if(optParticipant.isPresent()) {
            return new ResponseEntity<>(optParticipant.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("conferences/{conference-id}/participants")
    public ResponseEntity<List<Participant>> conferenceParticipants() {
        List<Participant> participants = new ArrayList<>();

        participantService.findAll().forEach(participants::add);

        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    //might not need it anymore (check again with Carmen)
    /*@GetMapping("tracks/{track-id}/sessions/{session-id}/participants")
    public ResponseEntity<List<Participant>> sessionParticipants() {
        List<Participant> participants = new ArrayList<>();

        participantsRepo.findAll().forEach(participants::add);

        return new ResponseEntity<>(participants, HttpStatus.OK);
    }*/

    // POST method may be implemented with ResponseEntity
    //@PostMapping(consumes = "application/json")
    //@ResponseStatus(HttpStatus.CREATED)
    //public Participant postParticipant(@RequestBody Participant participant) {
    //   return participantsRepo.save(participant);
    //}

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ParticipantDTO> postParticipant(@Valid @RequestBody ParticipantCreationDTO participantCreationDTO, Errors errors) {

        Participant participant = mapper.toParticipant(participantCreationDTO);
        ParticipantDTO participantDTO = mapper.toDto(participant);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        participantService.save(participant);
        return new ResponseEntity<>(participantDTO, HttpStatus.CREATED);
    }

    @PutMapping("conferences/{conference-id}/participants/{participant-id}")
    public ResponseEntity<ParticipantDTO> putParticipant(@Valid @RequestBody ParticipantCreationDTO participantCreationDTO, Errors errors) {

        Participant participant = mapper.toParticipant(participantCreationDTO);
        ParticipantDTO participantDTO = mapper.toDto(mapper.toParticipant(participantCreationDTO));
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        participantService.save(participant);
        return new ResponseEntity<>(participantDTO, HttpStatus.OK);
    }

    // implement validation check
    @PatchMapping("conferences/{conference-id}/participants/{participant-id}")
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


    // DELETE method may be implemented with ResponseEntity
    // handle exception
    @DeleteMapping("conferences/{conference-id}/participants/{participant-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteParticipant (@PathVariable("participant-id") UUID id) {
        try {
            participantService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}

    }


}
