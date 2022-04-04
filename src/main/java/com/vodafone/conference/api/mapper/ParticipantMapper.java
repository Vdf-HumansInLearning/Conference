package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.entities.DTO.ParticipantCreationDTO;
import com.vodafone.conference.models.entities.DTO.ParticipantDTO;
import com.vodafone.conference.models.entities.Participant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ParticipantMapper {

    ModelMapper mapper = new ModelMapper();

    public ParticipantDTO toDto(Participant participant) {

        ParticipantDTO participantDTO = mapper.map(participant, ParticipantDTO.class);
        return participantDTO;
    }

    public Participant toParticipant(ParticipantCreationDTO participantCreationDTO) {

        Participant participant = mapper.map(participantCreationDTO, Participant.class);
        return participant;
    }
}
