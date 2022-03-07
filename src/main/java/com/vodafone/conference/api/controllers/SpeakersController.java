package com.vodafone.conference.api.controllers;

import java.util.Optional;
import java.util.Optional;
import java.util.UUID;

import com.vodafone.conference.api.repositories.SpeakerRepository;
import com.vodafone.conference.models.entities.Speaker;
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
@RequestMapping(path="/speakers", produces = "application/json")
@CrossOrigin(origins = "*")
public class SpeakersController {

    private SpeakerRepository speakersRepo;

    //@Autowired
    //EntityLinks entityLinks;

    public  SpeakersController(SpeakerRepository speakerRepository) {this.speakersRepo = speakerRepository; }

    @GetMapping("sessions/{session-id}/speakers/{speaker-id}")
    public ResponseEntity<Speaker> speakerById(@PathVariable("speaker-id") UUID id) {
        Optional<Speaker> optSpeaker = speakersRepo.findById(id);
        if(optSpeaker.isPresent()) {
            return new ResponseEntity<>(optSpeaker.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // GET method for returning all speakers for session
    /*@GetMapping("sessions/{session-id}/participants")
    public Speaker sessionSpeakers() {

    }*/

    // POST method may be implemented ResponseEntity
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker postSpeaker(@RequestBody Speaker speaker) {
        return speakersRepo.save(speaker);
    }

    @PutMapping("sessions/{session-id}/speakers/{speaker-id}")
    public Speaker putParticipant(@RequestBody Speaker speaker) {
        return speakersRepo.save(speaker);
    }

    // TO DO PATCH method

    // DELETE method may be implemented with ResponseEntity
    // handle exception
    @DeleteMapping("sessions/{session-id}/speakers/{speaker-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSpeaker (@PathVariable("speaker-id") UUID id) {
        try {
            speakersRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {}

    }
}
