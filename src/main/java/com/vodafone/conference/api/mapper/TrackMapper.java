package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.dto.DayCreationDTO;
import com.vodafone.conference.models.dto.DayDTO;
import com.vodafone.conference.models.dto.TrackCreationDTO;
import com.vodafone.conference.models.dto.TrackDTO;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Track;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TrackMapper {
    ModelMapper mapper = new ModelMapper();

    public TrackDTO toDto(Track track) {
        return mapper.map(track, TrackDTO.class);
    }

    public Track toTrack(TrackCreationDTO trackCreationDTO) {
        return mapper.map(trackCreationDTO, Track.class);
    }

    public Track toTrack(TrackDTO trackDTO) {
        return mapper.map(trackDTO, Track.class);
    }
}
