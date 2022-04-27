package com.vodafone.conference.api.controllers;

import com.vodafone.conference.api.mapper.SessionTypeMapper;
import com.vodafone.conference.models.dto.SessionTypeCreationDTO;
import com.vodafone.conference.models.dto.SessionTypeDTO;
import com.vodafone.conference.models.entities.SessionType;
import com.vodafone.conference.services.SessionTypeService;
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
@RequestMapping(path = "/session_types", produces = "application/json")
public class SessionTypeController {

    @Autowired
    private final SessionTypeService sessionTypeService;
    private final SessionTypeMapper mapper;

    public SessionTypeController(SessionTypeService sessionTypeService, SessionTypeMapper mapper) {
        this.sessionTypeService = sessionTypeService;
        this.mapper = mapper;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<SessionTypeDTO> createSessionType(@Valid @RequestBody SessionTypeCreationDTO sessionTypeCreationDTO, Errors errors) {
        SessionType sessionType = mapper.toSessionType(sessionTypeCreationDTO);
        SessionTypeDTO sessionTypeDTO = mapper.toDto(sessionType);

        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        sessionTypeService.save(sessionType);
        return new ResponseEntity<>(sessionTypeDTO, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<SessionTypeDTO>> getSessionTypes() {
        List<SessionTypeDTO> sessionTypeDTOList = sessionTypeService.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(sessionTypeDTOList, HttpStatus.OK);
    }

    @GetMapping("{session-type-id}")
    public ResponseEntity<SessionTypeDTO> getSessionTypeById(@PathVariable("session-type-id") String id) {
        Optional<SessionType> optSessionType = sessionTypeService.findById(UUID.fromString(id));
        return optSessionType.map(sessionType -> new ResponseEntity<>(mapper.toDto(sessionType), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("{session-type-id}")
    public ResponseEntity<SessionTypeDTO> putSessionTypeById(@Valid @RequestBody SessionTypeCreationDTO sessionTypeCreationDTO, Errors errors, @PathVariable("session-type-id") String id) {
        SessionType sessionType = mapper.toSessionType(sessionTypeCreationDTO);
        SessionTypeDTO sessionTypeDTO = mapper.toDto(sessionType);
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sessionTypeService.update(sessionType, UUID.fromString(id));
        return new ResponseEntity<>(sessionTypeDTO, HttpStatus.OK);
    }

    @DeleteMapping("{session-type-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity deleteSessionType(@PathVariable("session-type-id") String id) throws Exception {
        try {
            sessionTypeService.deleteById(UUID.fromString(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmptyResultDataAccessException e) {
            throw new Exception(e);
        }
    }

}
