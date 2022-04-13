package com.vodafone.conference.models.entities.DTO;

import com.vodafone.conference.models.entities.Participant;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

public class SpeakerDTO {

    private ParticipantDTO participantDTO;
    //private String title;
    private String company;
    private String linkedinAcc;
    private String twitterAcc;
    private String githubAcc;
    private String biography;

    public SpeakerDTO(ParticipantDTO participantDTO, String company, String linkedinAcc, String twitterAcc, String githubAcc, String biography) {
        this.participantDTO = participantDTO;
        //this.title = title;
        this.company = company;
        this.linkedinAcc = linkedinAcc;
        this.twitterAcc = twitterAcc;
        this.githubAcc = githubAcc;
        this.biography = biography;
    }

    public SpeakerDTO() {
    }

    public ParticipantDTO getParticipantDTO() {
        return participantDTO;
    }

    //public String getTitle() { return title; }

    public String getCompany() {
        return company;
    }

    public String getLinkedinAcc() {
        return linkedinAcc;
    }

    public String getTwitterAcc() {
        return twitterAcc;
    }

    public String getGithubAcc() {
        return githubAcc;
    }

    public String getBiography() {
        return biography;
    }

    public void setParticipantDTO(ParticipantDTO participantDTO) {
        this.participantDTO = participantDTO;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLinkedinAcc(String linkedinAcc) {
        this.linkedinAcc = linkedinAcc;
    }

    public void setTwitterAcc(String twitterAcc) {
        this.twitterAcc = twitterAcc;
    }

    public void setGithubAcc(String githubAcc) {
        this.githubAcc = githubAcc;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
