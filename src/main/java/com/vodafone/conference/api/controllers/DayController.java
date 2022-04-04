package com.vodafone.conference.api.controllers;

//import com.vodafone.conference.models.dto.BaseDayDTO;
import com.vodafone.conference.models.entities.Day;
//import com.vodafone.conference.services.DayService;
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

    /*@Autowired
    DayService dayService;

    @GetMapping(value = "/days/{day-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DayDTO> getDayById(@PathVariable String id) {

    }

    @GetMapping(value = "/days", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BaseDayDTO>> getAllDays() {
        return new ResponseEntity<List<BaseDayDTO>>(dayService.getAllDays(), HttpStatus.OK);
    }*/
}
