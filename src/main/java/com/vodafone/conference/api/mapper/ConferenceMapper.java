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
        ConferenceDTO conferenceDTO = modelMapper.map(conference, ConferenceDTO.class);

        return conferenceDTO;
    }

    public Conference toConference(ConferenceCreationDTO conferenceCreationDTO) {
        Conference conference = modelMapper.map(conferenceCreationDTO, Conference.class);
        return conference;
    }
}
