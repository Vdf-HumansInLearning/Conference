package com.vodafone.conference.models.utils;

import com.vodafone.conference.models.dto.BaseTrackDTO;
import com.vodafone.conference.models.entities.Track;
import org.springframework.stereotype.Service;

@Service
public class TrackConverter implements EntityConverter<Track, BaseTrackDTO> {

    @Override
    public BaseTrackDTO convertToDTO(Track track) {
        BaseTrackDTO baseTrackDTO = new BaseTrackDTO();
        baseTrackDTO.title = track.getTitle();
        baseTrackDTO.day = track.getDay();
        return baseTrackDTO;
    }

    @Override
    public Track convertToEntity(BaseTrackDTO dto) {
        Track trackEntity = new Track();
        trackEntity.setTitle(dto.title);
        trackEntity.setDay(dto.day);
        return trackEntity;
    }
}