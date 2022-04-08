package com.vodafone.conference.api.controllers;

import com.vodafone.conference.exceptions.ApiRequestException;
import com.vodafone.conference.models.dto.DayDTO;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.services.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class DayController {

    @Autowired
    DayService dayService;

    @GetMapping(value = "/days/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DayDTO> getTrackById(@PathVariable UUID id) {
        Day byId = dayService.findById(id);
        if (byId != null) {
            DayDTO dayDTO = new DayDTO(byId);
            return new ResponseEntity<DayDTO>(dayDTO, HttpStatus.OK);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }

    @GetMapping(value = "/days", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DayDTO>> getAllDays() {
        return new ResponseEntity<List<DayDTO>>(dayService.getAllDays(), HttpStatus.OK);
    }

    @GetMapping(value = "/days/by-date", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DayDTO> getByDay(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) {
        Day byDay = dayService.findByDay(date);
        if (byDay != null) {
            DayDTO dayDTO = new DayDTO(byDay);
            return new ResponseEntity<DayDTO>(dayDTO, HttpStatus.OK);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.DATE_NOT_FOUND, date.toString()));
        }
    }

    @PutMapping(value = "days/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Day> updateDayById(@PathVariable(name = "id") UUID id, @RequestBody DayDTO dayDTO) {
        Day dayById = dayService.findById(id);
        if (dayById != null) {
            Day day = dayService.updateDayById(id, dayDTO);
            return new ResponseEntity<Day>(day, HttpStatus.OK);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }
    
    @PostMapping(value = "days/add-day", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Day> createDay(@RequestBody DayDTO dayDTO) {
        Day day = dayService.saveNewDay(dayDTO);
        return new ResponseEntity<Day>(day, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/days/{id}")
    public void deleteDayById(@PathVariable UUID id) {
        if (dayService.isIdPresent(id)) {
            dayService.deleteById(id);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }
}
