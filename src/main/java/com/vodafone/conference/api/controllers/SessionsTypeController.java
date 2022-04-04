package com.vodafone.conference.api.controllers;

import com.vodafone.conference.api.mapper.ConferenceMapper;
import com.vodafone.conference.api.mapper.SessionsTypeMapper;
import com.vodafone.conference.api.repositories.SessionsTypeRepository;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.DTO.*;
import com.vodafone.conference.models.entities.Session;
import com.vodafone.conference.models.entities.SessionType;
import com.vodafone.conference.services.ConferenceService;
import com.vodafone.conference.services.SessionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SessionsTypeController {

    @Autowired
    private SessionsTypeRepository sessionsTypeRepository;
    private SessionTypeService sessionTypeService;
    private SessionsTypeMapper mapper;

    @GetMapping("session_types")
    public ResponseEntity<List<SessionType>> getSessionTypes() {
        return new ResponseEntity<>(sessionsTypeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("session_types/{session_type_id}")
    public ResponseEntity<SessionType> getSessionTypeById(@PathVariable("session_type_id") String id) {
        Optional<SessionType> sessionType = sessionsTypeRepository.findById(UUID.fromString(id));
        return sessionType.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<SessionsTypeDTO> createSessionType(@Valid @RequestBody SessionsTypeCreationDTO sessionsTypeCreationDTO, Errors errors) {

        SessionType sessionType = mapper.toSessionType(sessionsTypeCreationDTO);
        //System.out.println(conference.toString());
        SessionsTypeDTO sessionsTypeDTO = mapper.toDto(sessionType);
        //System.out.println(conferenceDTO.toString());
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        sessionTypeService.save(sessionType);
        return new ResponseEntity<>(sessionsTypeDTO, HttpStatus.CREATED);
    }
}
