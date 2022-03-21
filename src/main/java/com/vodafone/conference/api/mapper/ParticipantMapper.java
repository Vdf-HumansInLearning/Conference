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
        /*String firstName = participant.getFirstName();
        String lastName = participant.getLastName();
        String title = participant.getTitle();
        String email = participant.getEmail();
        String phoneNumber = participant.getPhoneNumber();
        String username = participant.getUsername();

        return new ParticipantDTO(firstName, lastName, title, email, phoneNumber, username);*/

        ParticipantDTO participantDTO = mapper.map(participant, ParticipantDTO.class);
        return participantDTO;
    }

    // check with Carmen if UUID should be generated here
    // test against Lombok generated constructor
    // TO DO must add other parameters to AllArgsConstructor call
    public Participant toParticipant(ParticipantCreationDTO participantCreationDTO) {
        /*return new Participant(UUID.randomUUID(), participantCreationDTO.getFirstName(),
                participantCreationDTO.getLastName(), participantCreationDTO.getTitle(),
                participantCreationDTO.getEmail(), participantCreationDTO.getPhoneNumber(),
                participantCreationDTO.getUsername(), participantCreationDTO.getPassword(),
                );*/

        Participant participant = mapper.map(participantCreationDTO, Participant.class);
        return participant;
    }
}
