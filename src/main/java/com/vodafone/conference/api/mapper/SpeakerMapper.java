package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.entities.DTO.ParticipantDTO;
import com.vodafone.conference.models.entities.DTO.SpeakerCreationDTO;
import com.vodafone.conference.models.entities.DTO.SpeakerDTO;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Speaker;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SpeakerMapper {

    ModelMapper mapper = new ModelMapper();

    public SpeakerDTO toDto(Speaker speaker) {

        SpeakerDTO speakerDTO = mapper.map(speaker, SpeakerDTO.class);
        return speakerDTO;
    }

    public Speaker toSpeaker(SpeakerCreationDTO speakerCreationDTO) {

        Speaker speaker = mapper.map(speakerCreationDTO, Speaker.class);
        return speaker;
    }
}
