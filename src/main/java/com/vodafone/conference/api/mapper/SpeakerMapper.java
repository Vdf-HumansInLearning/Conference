package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.entities.DTO.ParticipantDTO;
import com.vodafone.conference.models.entities.DTO.SpeakerCreationDTO;
import com.vodafone.conference.models.entities.DTO.SpeakerDTO;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Speaker;

import java.util.UUID;

public class SpeakerMapper {

    public SpeakerDTO toDto(Speaker speaker) {
        Participant participant = speaker.getParticipant();
        String company = speaker.getCompany();
        String linkedinAcc = speaker.getLinkedinAcc();
        String twitterAcc = speaker.getTwitterAcc();
        String githubAcc = speaker.getGithubAcc();
        String biography = speaker.getBiography();

        return new SpeakerDTO(participant, company, linkedinAcc, twitterAcc, githubAcc, biography);
    }

    // check with Carmen if UUID should be generated here
    // test against Lombok generated constructor
    // TO DO must add other parameters to AllArgsConstructor call
    public Speaker toSpeaker(SpeakerCreationDTO speakerCreationDTO) {
        /*return new Speaker(UUID.randomUUID(), speakerCreationDTO.getParticipant(),
                speakerCreationDTO.getTitle(), speakerCreationDTO.getCompany(),
                speakerCreationDTO.getLinkedinAcc(), speakerCreationDTO.getTwitterAcc(),
                speakerCreationDTO.getGithubAcc(), speakerCreationDTO.getBiography(),
                );*/
    }
}
