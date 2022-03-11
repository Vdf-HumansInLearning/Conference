package com.vodafone.conference.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.vodafone.conference.api.repositories.ParticipantRepository;
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
    private ParticipantRepository participantsRepo;

    //@Autowired
    //EntityLinks entityLinks;

    public ParticipantsController(ParticipantRepository participantsRepo) {
        this.participantsRepo = participantsRepo;
    }

    // May want to rework endpoint for returning participants belonging to a conference and to a session

    @GetMapping("conferences/{conference-id}/participants/{participant-id}")
    public ResponseEntity<Participant> conferenceParticipantById(@PathVariable("participant-id") UUID id) {
        Optional<Participant> optParticipant = participantsRepo.findById(id);
        if(optParticipant.isPresent()) {
            return new ResponseEntity<>(optParticipant.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("tracks/{track-id}/sessions/{session-id}/participants/{participant-id}")
    public ResponseEntity<Participant> sessionParticipantById(@PathVariable("participant-id") UUID id) {
        Optional<Participant> optParticipant = participantsRepo.findById(id);
        if(optParticipant.isPresent()) {
            return new ResponseEntity<>(optParticipant.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("conferences/{conference-id}/participants")
    public ResponseEntity<List<Participant>> conferenceParticipants() {
        List<Participant> participants = new ArrayList<>();

        participantsRepo.findAll().forEach(participants::add);

        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    @GetMapping("tracks/{track-id}/sessions/{session-id}/participants")
    public ResponseEntity<List<Participant>> sessionParticipants() {
        List<Participant> participants = new ArrayList<>();

        participantsRepo.findAll().forEach(participants::add);

        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    // POST method may be implemented with ResponseEntity
    //@PostMapping(consumes = "application/json")
    //@ResponseStatus(HttpStatus.CREATED)
    //public Participant postParticipant(@RequestBody Participant participant) {
    //   return participantsRepo.save(participant);
    //}

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Participant> postParticipant(@Valid @RequestBody Participant participant, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(participantsRepo.save(participant), HttpStatus.CREATED);
    }

    @PutMapping("conferences/{conference-id}/participants/{participant-id}")
    public ResponseEntity<Participant> putParticipant(@Valid @RequestBody Participant participant, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(participantsRepo.save(participant), HttpStatus.OK);
    }

    // implement validation check
    @PatchMapping("conferences/{conference-id}/participants/{participant-id}")
    public ResponseEntity<Participant> patchParticipant(@PathVariable("participant-id") UUID id, @Valid @RequestBody Participant patch ) {
        Participant participant = participantsRepo.findById(id).get();
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

        return new ResponseEntity<>(participantsRepo.save(participant), HttpStatus.OK);
    }


    // DELETE method may be implemented with ResponseEntity
    // handle exception
    @DeleteMapping("conferences/{conference-id}/participants/{participant-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteParticipant (@PathVariable("participant-id") UUID id) {
        try {
            participantsRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}

    }


}
