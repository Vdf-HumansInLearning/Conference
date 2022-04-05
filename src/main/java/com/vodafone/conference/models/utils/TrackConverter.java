package com.vodafone.conference.models.utils;

import com.vodafone.conference.models.dto.TrackDTO;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Track;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TrackConverter implements EntityConverter<Track, TrackDTO> {

    @Override
    public TrackDTO convertToDTO(Track track) {
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.title = track.getTitle();
        trackDTO.day = track.getDay().getDate();
        return trackDTO;
    }

    @Override
    public Track convertToEntity(TrackDTO dto) {
        Track trackEntity = new Track();
        LocalDate date = LocalDate.of(dto.day.getYear(), dto.day.getMonthValue(), dto.day.getDayOfMonth());
        trackEntity.setTitle(dto.title);
        trackEntity.setDay(new Day(date));
        return trackEntity;
    }
}