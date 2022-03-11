package com.vodafone.conference.api.controllers;

import java.util.*;
import java.util.Optional;

import com.vodafone.conference.api.repositories.SpeakerRepository;
import com.vodafone.conference.models.entities.Speaker;
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
@RequestMapping(path="/speakers", produces = "application/json")
@CrossOrigin(origins = "*")
public class SpeakersController {

    @Autowired
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

    @GetMapping("sessions/{session-id}/speakers")
    public ResponseEntity<List<Speaker>> sessionSpeakers() {
        List<Speaker> speakers = new ArrayList<>();

        speakersRepo.findAll().forEach(speakers::add);

        return new ResponseEntity<>(speakers, HttpStatus.OK);
    }

    // POST method may be implemented ResponseEntity
    //@PostMapping(consumes = "application/json")
    //@ResponseStatus(HttpStatus.CREATED)
    //public Speaker postSpeaker(@RequestBody Speaker speaker) {
    //    return speakersRepo.save(speaker);
    //}

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Speaker> postSpeaker(@Valid @RequestBody Speaker speaker, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(speakersRepo.save(speaker), HttpStatus.CREATED);
    }

    //@PutMapping("sessions/{session-id}/speakers/{speaker-id}")
    //public Speaker putParticipant(@RequestBody Speaker speaker) {
    //    return speakersRepo.save(speaker);
    //}

    @PutMapping("sessions/{session-id}/speakers/{speaker-id}")
    public ResponseEntity<Speaker> putParticipant(@Valid @RequestBody Speaker speaker, Errors errors)
    {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(speakersRepo.save(speaker), HttpStatus.OK);
    }

    // TO DO PATCH method
    // implement validation check
    @PatchMapping("sessions/{session-id}/speakers/{speaker-id}")
    public ResponseEntity<Speaker> patchSpeaker(@PathVariable("speaker-id") UUID id, @Valid @RequestBody Speaker patch) {
        Speaker speaker = speakersRepo.findById(id).get();

        //check inner participant fields
        if (patch.getParticipant().getFirstName() != null) {
            speaker.getParticipant().setFirstName(patch.getParticipant().getFirstName());
        }
        if (patch.getParticipant().getLastName() != null) {
            speaker.getParticipant().setLastName(patch.getParticipant().getLastName());
        }
        if (patch.getTitle() != null) {
            speaker.getParticipant().setTitle(patch.getTitle());
        }
        if (patch.getParticipant().getEmail() != null) {
            speaker.getParticipant().setEmail(patch.getParticipant().getEmail());
        }
        if (patch.getParticipant().getPhoneNumber() != null) {
            speaker.getParticipant().setPhoneNumber(patch.getParticipant().getPhoneNumber());
        }
        if (patch.getParticipant().getUsername() != null) {
            speaker.getParticipant().setUsername(patch.getParticipant().getUsername());
        }
        if (patch.getParticipant().getPassword() != null) {
            speaker.getParticipant().setPassword(patch.getParticipant().getPassword());
        }

        // check speaker specific fields
        if (patch.getCompany() != null) {
            speaker.setCompany(patch.getCompany());
        }
        if (patch.getLinkedinAcc() != null) {
            speaker.setLinkedinAcc(patch.getLinkedinAcc());
        }
        if (patch.getTwitterAcc() != null) {
            speaker.setTwitterAcc(patch.getTwitterAcc());
        }
        if (patch.getGithubAcc() != null) {
            speaker.setGithubAcc(patch.getGithubAcc());
        }
        if (patch.getBiography() != null) {
            speaker.setBiography(patch.getBiography());
        }

        return new ResponseEntity<>(speakersRepo.save(speaker), HttpStatus.OK);
    }

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
