package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.DTO.ConferenceCreationDTO;
import com.vodafone.conference.models.entities.DTO.ConferenceDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ConferenceMapper {

    ModelMapper mapper = new ModelMapper();

    public ConferenceDTO toDto(Conference conference) {

        ConferenceDTO conferenceDTO = mapper.map(conference, ConferenceDTO.class);

        return conferenceDTO;
    }

    public Conference toConference(ConferenceCreationDTO conferenceCreationDTO) {

        Conference conference = mapper.map(conferenceCreationDTO, Conference.class);
        return conference;
    }
}
