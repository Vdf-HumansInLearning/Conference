package com.vodafone.conference.models.utils;

import com.vodafone.conference.models.dto.TrackDTO;
import com.vodafone.conference.models.entities.Track;
import org.springframework.stereotype.Service;


@Service
public class TrackConverter implements EntityConverter<Track, TrackDTO> {

    @Override
    public TrackDTO convertToDTO(Track track) {
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.title = track.getTitle();
        trackDTO.day = track.getDay();
        trackDTO.conference = track.getConference();
        return trackDTO;
    }

    @Override
    public Track convertToEntity(TrackDTO dto) {
        Track trackEntity = new Track();
        trackEntity.setTitle(dto.title);
        trackEntity.setDay(dto.day);
        trackEntity.setConference(dto.conference);
        return trackEntity;
    }
}