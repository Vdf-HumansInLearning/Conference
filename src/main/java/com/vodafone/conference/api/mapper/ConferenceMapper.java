package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.dto.ConferenceCreationDTO;
import com.vodafone.conference.models.dto.ConferenceDTO;
import com.vodafone.conference.models.entities.Conference;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConferenceMapper {

    ModelMapper modelMapper = new ModelMapper();

    public ConferenceDTO toDto(Conference conference) {
        return modelMapper.map(conference, ConferenceDTO.class);
    }

    public Conference toConference(ConferenceCreationDTO conferenceCreationDTO) {
        return modelMapper.map(conferenceCreationDTO, Conference.class);
    }
}
