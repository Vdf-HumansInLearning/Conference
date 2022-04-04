package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.TrackRepository;
import com.vodafone.conference.models.dto.BaseTrackDTO;
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
            throw new RuntimeException(String.format("Could not find a track with the id: ", id));
        }
    }

    @Transactional
    public List<BaseTrackDTO> getAllTracks() {
        List<Track> tracks = trackRepository.findAll();
        List<BaseTrackDTO> baseTrackDTOList = new ArrayList<>();

        for (Track track : tracks) {
            BaseTrackDTO temp = new BaseTrackDTO();
            temp.title = track.getTitle();
            temp.day = track.getDay();
            temp.sessions = track.getSessions();
            baseTrackDTOList.add(temp);
        }
        return baseTrackDTOList;
    }

//    @Transactional
//    public Track saveTrack(BaseTrackDTO dto) {
//        Track trackToBeSaved = new Track(dto);
//        Track trackSaved = trackRepository.save(trackToBeSaved);
//        return trackSaved;
//    }

    @Transactional
    public void deleteById(UUID id) {
        Optional<Track> byId = trackRepository.findById(id);
        if (byId.isPresent()) {
            trackRepository.deleteById(id);
        } else {
            throw new RuntimeException(String.format("Could not find a track with the id: ", id));
        }
    }
}