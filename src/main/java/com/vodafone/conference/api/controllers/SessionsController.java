package com.vodafone.conference.api.controllers;

import com.vodafone.conference.api.repositories.SessionRepository;
import com.vodafone.conference.models.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
//@RequestMapping("/conferences/{conference_id}/days/{day_id}/tracks/{track_id}/sessions")
public class SessionsController {

    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping("sessions")
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        return new ResponseEntity<>(sessionRepository.save(session), HttpStatus.CREATED);
    }

    @DeleteMapping("sessions/{session_id}")
    public HttpStatus deleteSession(@PathVariable UUID uuid) {
        Optional<Session> session = sessionRepository.findById(uuid);
        if (session.isPresent()) {
            sessionRepository.deleteById(uuid);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    @PutMapping("sessions/{session_id}")
    public ResponseEntity<Session> updateSession(@PathVariable UUID uuid, @RequestBody Session sessionDetails) {
        Optional<Session> session = sessionRepository.findById(uuid);
        if (session.isPresent()) {
            session.get().setTitle(sessionDetails.getTitle());
            session.get().setSpeakers(sessionDetails.getSpeakers());
            session.get().setDescription(sessionDetails.getDescription());
            session.get().setSessionType(sessionDetails.getSessionType());
            session.get().setTopic(sessionDetails.getTopic());
            session.get().setTechLevel(sessionDetails.getTechLevel());
            session.get().setKeywords(sessionDetails.getKeywords());
            session.get().setDate(sessionDetails.getDate());
            session.get().setEndTime(sessionDetails.getEndTime());
            session.get().setReview(sessionDetails.getReview());
            //session.get().setParticipants(sessionDetails.getParticipants());
            //session.get().setTrack(sessionDetails.getTrack());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("sessions/{session_id}")
    public ResponseEntity<Session> getSessionById(@PathVariable UUID uuid) {
        Optional<Session> session = sessionRepository.findById(uuid);
        return session.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//        TODO: Count de participanti
    }
}
