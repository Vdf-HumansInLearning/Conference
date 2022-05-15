package com.vodafone.conference.api.controllers;

import com.vodafone.conference.api.mapper.DayMapper;
import com.vodafone.conference.api.mapper.TrackMapper;
import com.vodafone.conference.exceptions.ApiRequestException;
import com.vodafone.conference.models.dto.DayDTO;
import com.vodafone.conference.models.dto.TrackCreationDTO;
import com.vodafone.conference.models.dto.TrackDTO;
import com.vodafone.conference.models.entities.Day;
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

    private final TrackMapper trackMapper;

    @Autowired
    private TrackService trackService;

    public TrackController(TrackMapper trackMapper, TrackService trackService) {
        this.trackMapper = trackMapper;
        this.trackService = trackService;
    }

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

    @PutMapping(value = "/tracks/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrackDTO> updateTrackById(@PathVariable(name = "id") UUID id, @RequestBody TrackCreationDTO trackCreationDTO) {
        Track trackById = trackService.findById(id);
        if(trackById != null) {
            Track track = trackMapper.toTrack(trackCreationDTO);
            TrackDTO trackDTO = trackMapper.toDto(track);
            trackService.updateTrackById(id, trackDTO);
            return new ResponseEntity<TrackDTO>(trackDTO, HttpStatus.OK);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }

    @PostMapping(value = "/tracks/add-track", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrackDTO> createTrack(@RequestBody TrackCreationDTO trackCreationDTO) {
        Track track = trackMapper.toTrack(trackCreationDTO);
        TrackDTO trackDTO = trackMapper.toDto(track);
        trackService.saveNewTrack(trackDTO);
        return new ResponseEntity<TrackDTO>(trackDTO, HttpStatus.CREATED);
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