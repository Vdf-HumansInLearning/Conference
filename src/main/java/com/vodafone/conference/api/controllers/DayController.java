package com.vodafone.conference.api.controllers;

import com.vodafone.conference.models.dto.BaseDayDTO;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.services.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class DayController {

    @Autowired
    DayService dayService;

    @GetMapping(value = "/days/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseDayDTO> getTrackById(@PathVariable UUID id) {
        Day byId = dayService.findById(id);
        if (byId != null) {
            BaseDayDTO dayDTO = new BaseDayDTO(byId);
            return new ResponseEntity<BaseDayDTO>(dayDTO, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No day with the id: %s was found", id));
        }
    }

    @GetMapping(value = "/days", produces = "application/json")
    public ResponseEntity<List<BaseDayDTO>> getAllDays() {
        return new ResponseEntity<List<BaseDayDTO>>(dayService.getAllDays(), HttpStatus.OK);
    }
}