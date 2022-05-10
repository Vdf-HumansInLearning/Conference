package com.vodafone.conference.api.controllers;

import com.vodafone.conference.api.mapper.SessionMapper;
import com.vodafone.conference.models.dto.SessionCreationDTO;
import com.vodafone.conference.models.dto.SessionDTO;
import com.vodafone.conference.models.entities.Session;
import com.vodafone.conference.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/session", produces = "application/json")
public class SessionController {

    @Autowired
    private final SessionService sessionService;
    private final SessionMapper mapper;

    public SessionController(SessionService sessionService, SessionMapper mapper) {
        this.sessionService = sessionService;
        this.mapper = mapper;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<SessionDTO> createSession(@Valid @RequestBody SessionCreationDTO sessionCreationDTO, Errors errors) {
        Session session = mapper.toSession(sessionCreationDTO);
        SessionDTO sessionDTO = mapper.toDto(session);

        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        sessionService.save(session);
        return new ResponseEntity<>(sessionDTO, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<SessionDTO>> getSessions() {
        List<SessionDTO> sessionDTOList = sessionService.findAll().stream().map(mapper::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(sessionDTOList, HttpStatus.OK);
    }

    @GetMapping("{session-id}")
    public ResponseEntity<SessionDTO> getSessionById(@PathVariable("session-id") String id) {
        Optional<Session> optSession = sessionService.findById(UUID.fromString(id));
        return optSession.map(session -> new ResponseEntity<>(mapper.toDto(session), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("{session-id}")
    public ResponseEntity<SessionDTO> putSessionById(@Valid @RequestBody SessionCreationDTO sessionCreationDTO, Errors errors, @PathVariable("session-id") String id) {
        Session session = mapper.toSession(sessionCreationDTO);
        SessionDTO sessionDTO = mapper.toDto(session);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sessionService.update(session, UUID.fromString(id));
        return new ResponseEntity<>(sessionDTO, HttpStatus.OK);
    }

    @DeleteMapping("{session-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSession(@PathVariable("session-id") String id) throws Exception {
        try {
            sessionService.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {
            throw new Exception(e);
        }
    }

}
