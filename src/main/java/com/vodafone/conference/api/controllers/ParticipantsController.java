package com.vodafone.conference.api.controllers;

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
import org.springframework.web.bind.annotation.*;

import com.vodafone.conference.models.entities.Participant;

@RestController
// base path URL will break requests
@RequestMapping(path="/participants", produces = "application/json")
@CrossOrigin(origins = "*")
public class ParticipantsController {

    private ParticipantRepository participantsRepo;

    //@Autowired
    //EntityLinks entityLinks;

    public ParticipantsController(ParticipantRepository participantsRepo) {
        this.participantsRepo = participantsRepo;
    }

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

    // GET methods for returning participants from conference and session
    /*@GetMapping("conferences/{conference-id}/participants")
    public Participant conferenceParticipants() {

    }

    @GetMapping("tracks/{track-id}/sessions/{session-id}/participants")
    public Participant sessionParticipants() {

    }*/

    // POST method may be implemented ResponseEntity
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Participant postParticipant(@RequestBody Participant participant) {
       return participantsRepo.save(participant);
    }

    @PutMapping("conferences/{conference-id}/participants/{participant-id}")
    public Participant putParticipant(@RequestBody Participant participant) {
        return participantsRepo.save(participant);
    }

    // TO DO PATCH method

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
