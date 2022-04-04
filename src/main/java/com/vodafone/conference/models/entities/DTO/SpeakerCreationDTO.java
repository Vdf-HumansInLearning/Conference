package com.vodafone.conference.models.entities.DTO;

import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Session;

import javax.persistence.*;
import java.util.List;

public class SpeakerCreationDTO {

    SpeakerCreationDTO() {}

    private ParticipantCreationDTO participantCreationDTO;
    private String title;
    private String company;
    private String linkedinAcc;
    private String twitterAcc;
    private String githubAcc;
    private String biography;

    public ParticipantCreationDTO getParticipantCreationDTO() {
        return participantCreationDTO;
    }

    public void setParticipantCreationDTO(ParticipantCreationDTO participantCreationDTO) {
        this.participantCreationDTO = participantCreationDTO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLinkedinAcc() {
        return linkedinAcc;
    }

    public void setLinkedinAcc(String linkedinAcc) {
        this.linkedinAcc = linkedinAcc;
    }

    public String getTwitterAcc() {
        return twitterAcc;
    }

    public void setTwitterAcc(String twitterAcc) {
        this.twitterAcc = twitterAcc;
    }

    public String getGithubAcc() {
        return githubAcc;
    }

    public void setGithubAcc(String githubAcc) {
        this.githubAcc = githubAcc;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    // Unsure whether these fields pertain to user creation
    // Check with Carmen
    /*private List<Session> sessions;

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }*/
}
