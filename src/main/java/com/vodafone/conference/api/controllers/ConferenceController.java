package com.vodafone.conference.api.controllers;

import com.vodafone.conference.api.mapper.ConferenceMapper;
import com.vodafone.conference.api.mapper.ParticipantMapper;
import com.vodafone.conference.exceptions.ApiRequestException;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.DTO.ConferenceCreationDTO;
import com.vodafone.conference.models.entities.DTO.ConferenceDTO;
import com.vodafone.conference.models.entities.DTO.ParticipantCreationDTO;
import com.vodafone.conference.models.entities.DTO.ParticipantDTO;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.services.ConferenceService;
import com.vodafone.conference.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="/conferences", produces = "application/json")
@CrossOrigin(origins = "*")
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;
    private ConferenceMapper mapper;

    public ConferenceController(ConferenceService conferenceService, ConferenceMapper mapper) {
        this.conferenceService = conferenceService;
        this.mapper = mapper;
    }

    //get a conference by id
    // check
    @GetMapping("{conference-id}")
    public ResponseEntity<ConferenceDTO> getConferenceById(@PathVariable("conference-id") String id) {
        Optional<Conference> optConference = conferenceService.findById(UUID.fromString(id));
        if(optConference.isPresent()) {
            //ParticipantDTO participantDTO = mapper.toDto(optParticipant.get());
            return new ResponseEntity<>(mapper.toDto(optConference.get()), HttpStatus.OK);
        }
        else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }

    // create a conference
    // check
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ConferenceDTO> createConference(@Valid @RequestBody ConferenceCreationDTO conferenceCreationDTO, Errors errors) {

        Conference conference = mapper.toConference(conferenceCreationDTO);
        ConferenceDTO conferenceDTO = mapper.toDto(conference);
        if (errors.hasFieldErrors()) {
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.BAD_INPUT));
        }

        conferenceService.save(conference);
        return new ResponseEntity<>(conferenceDTO, HttpStatus.CREATED);
    }

    //rewrite a conference by id
    // check
    @PutMapping("{conference-id}")
    public ResponseEntity<ConferenceDTO> putConference(@Valid @RequestBody ConferenceCreationDTO conferenceCreationDTO, Errors errors, @PathVariable("conference-id") String id) {

        Conference conference = mapper.toConference(conferenceCreationDTO);
        ConferenceDTO conferenceDTO = mapper.toDto(mapper.toConference(conferenceCreationDTO));
        if (errors.hasFieldErrors()) {
            //return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.BAD_INPUT));
        }
        conferenceService.update(conference, UUID.fromString(id));
        return new ResponseEntity<>(conferenceDTO, HttpStatus.OK);
    }

    // update a conference by id
    // implement validation check
    // check
    @PatchMapping("{conference-id}")
    public ResponseEntity<ConferenceDTO> patchConference(@PathVariable("conference-id") String id, @Valid @RequestBody ConferenceCreationDTO conferenceCreationDTO, Errors errors) {
        Conference conference = conferenceService.findById(UUID.fromString(id)).get();

        //error checks
        if (conference == null) {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
        if (errors.hasFieldErrors()) {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.BAD_INPUT));
        }


        if (conferenceCreationDTO.getDays() != null) {
            conference.setDays(conferenceCreationDTO.getDays());
        }
        if (conferenceCreationDTO.getLocation() != null) {
            conference.setLocation(conferenceCreationDTO.getLocation());
        }
        if (conferenceCreationDTO.getTheme() != null) {
            conference.setTheme(conferenceCreationDTO.getTheme());
        }
        if (conferenceCreationDTO.getDescription() != null) {
            conference.setDescription(conferenceCreationDTO.getDescription());
        }
        if (conferenceCreationDTO.getParticipants() != null) {
            conference.setParticipants(conferenceCreationDTO.getParticipants());
        }


        conferenceService.update(conference, UUID.fromString(id));
        return new ResponseEntity<>(mapper.toDto(conference), HttpStatus.OK);
    }

    // delete a participant by id
    // DELETE method may be implemented with ResponseEntity
    // handle exception
    // DOES NOT WORK FOR CERTAIN UUIDs (UUID string too large)
    @DeleteMapping("{conference-id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteConference (@PathVariable("conference-id") String id) {
        try {
            conferenceService.deleteById(UUID.fromString(id));
        } catch (EmptyResultDataAccessException e) {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }

    }
}
