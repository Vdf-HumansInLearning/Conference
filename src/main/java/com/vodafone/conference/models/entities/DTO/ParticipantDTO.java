package com.vodafone.conference.models.entities.DTO;

public class ParticipantDTO {
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String phoneNumber;
    private String username;
    private Boolean isOrganiser;
    private Boolean isSpeaker;

    public ParticipantDTO(String firstName, String lastName, String title, String email, String phoneNumber, String username, boolean isOrganiser, boolean isSpeaker) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.isOrganiser = isOrganiser;
        this.isSpeaker = isSpeaker;
    }

    public ParticipantDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getOrganiser() {
        return isOrganiser;
    }

    public Boolean getSpeaker() {
        return isSpeaker;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrganiser(Boolean organiser) {
        isOrganiser = organiser;
    }

    public void setSpeaker(Boolean speaker) {
        isSpeaker = speaker;
    }
}
