package com.vodafone.conference.api.controllers;

import com.vodafone.conference.exceptions.ApiRequestException;
import com.vodafone.conference.models.dto.TrackDTO;
import com.vodafone.conference.models.entities.Track;
import com.vodafone.conference.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping(value = "/tracks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrackDTO> getTrackById(@PathVariable UUID id) {
        Track byId = trackService.findById(id);
        if (byId != null) {
            TrackDTO trackDTO = new TrackDTO(byId);
            return new ResponseEntity<TrackDTO>(trackDTO, HttpStatus.OK);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }

    @GetMapping(value = "/tracks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TrackDTO>> getAllTracks() {
        return new ResponseEntity<List<TrackDTO>>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @PutMapping(value = "tracks/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Track> updateTrackById(@PathVariable(name = "id") UUID id, @RequestBody TrackDTO trackDTO) {
        Track trackById = trackService.findById(id);
        if (trackById != null) {
            Track track = trackService.updateTrackById(id, trackDTO);
            return new ResponseEntity<Track>(track, HttpStatus.OK);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }

    @DeleteMapping(value = "/tracks/{id}")
    public void deleteTrackById(@PathVariable UUID id) {
        if (trackService.isIdPresent(id)) {
            trackService.deleteById(id);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }
}