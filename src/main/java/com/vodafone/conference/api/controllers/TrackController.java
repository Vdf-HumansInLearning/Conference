package com.vodafone.conference.api.controllers;

import com.vodafone.conference.models.dto.BaseTrackDTO;
import com.vodafone.conference.models.entities.Track;
import com.vodafone.conference.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping(value = "/tracks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseTrackDTO> getTrackById(@PathVariable UUID id) {
        Track byId = trackService.findById(id);
        if (byId != null) {
            BaseTrackDTO trackDTO = new BaseTrackDTO(byId);
            return new ResponseEntity<BaseTrackDTO>(trackDTO, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No track with the id: %s was found", id));
        }
    }

    @GetMapping(value = "/tracks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BaseTrackDTO>> getAllTracks() {
        return new ResponseEntity<List<BaseTrackDTO>>(trackService.getAllTracks(), HttpStatus.OK);
    }

//    @PostMapping(value = "/tracks",consumes = "application/json",   produces="application/json")
//    // TODO DE VERIFICAT CALEA?!
//    public ResponseEntity<Track> createTrack(@RequestBody BaseTrackDTO dto) {
//        Track track = trackService.saveTrack(dto);
//        return new ResponseEntity<Track>(track, HttpStatus.CREATED);
//    }

    @DeleteMapping(value = "/tracks/{id}")
    public void deleteTrackById(@PathVariable UUID id) {
        if (trackService.isIdPresent(id)) {
            trackService.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No track with the id: %s was found", id));
        }
    }

    //TODO @PutMapping
    //TODO CREATE EXCEPTIONS CLASS
}