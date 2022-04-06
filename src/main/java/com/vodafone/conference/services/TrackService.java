package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.ConferenceRepository;
import com.vodafone.conference.api.repositories.DayRepository;
import com.vodafone.conference.api.repositories.TrackRepository;
import com.vodafone.conference.exceptions.ApiRequestException;
import com.vodafone.conference.models.dto.TrackDTO;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Transactional
    public Boolean isIdPresent(UUID id) {
        return trackRepository.findById(id).isPresent();
    }

    @Transactional
    public Track findById(UUID id) {
        Optional<Track> byId = trackRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }

    @Transactional
    public List<TrackDTO> getAllTracks() {
        List<Track> tracks = trackRepository.findAll();
        List<TrackDTO> trackDTOList = new ArrayList<>();

        for (Track track : tracks) {
            TrackDTO temp = new TrackDTO();
            temp.id = track.getId();
            temp.title = track.getTitle();
            temp.day = track.getDay();
            temp.sessions = track.getSessions();
            temp.conference = track.getConference();
            trackDTOList.add(temp);
        }
        return trackDTOList;
    }

    @Transactional
    public Track updateTrackById(UUID id, TrackDTO trackDTO) {
        Track track;
        Day day;
        Conference conference;

        Optional<Track> trackOptional = trackRepository.findById(id);
        Optional<Day> dayOptional = dayRepository.findById(trackDTO.day.getId());
        Optional<Conference> conferenceOptional = conferenceRepository.findById(trackDTO.conference.getId());
        if (!trackOptional.isPresent()) {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        } else {
            track = trackOptional.get();
            day = dayOptional.get();
            conference = conferenceOptional.get();
            track.setTitle(trackDTO.title);
            track.setDay(day);
            track.setConference(conference);
        }
        return trackRepository.save(track);
    }

    @Transactional
    public void deleteById(UUID id) {
        Optional<Track> byId = trackRepository.findById(id);
        if (byId.isPresent()) {
            trackRepository.deleteById(id);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }
}