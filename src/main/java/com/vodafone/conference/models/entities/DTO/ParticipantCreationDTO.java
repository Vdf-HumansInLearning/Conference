package com.vodafone.conference.models.entities.DTO;

import com.vodafone.conference.models.entities.*;

import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

public class ParticipantCreationDTO {

    // NOTE: making constructor public is incorrect but necessary for testing purposes
    public ParticipantCreationDTO() {}

    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;

    private Boolean isOrganiser;
    private Boolean isSpeaker;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Unsure whether these fields pertain to user creation
    // Check with Carmen
    //private List<Ticket> tickets;


    public Boolean getOrganiser() {
        return isOrganiser;
    }

    public void setOrganiser(Boolean organiser) {
        isOrganiser = organiser;
    }

    public Boolean getSpeaker() {
        return isSpeaker;
    }

    public void setSpeaker(Boolean speaker) {
        isSpeaker = speaker;
    }

    @Override
    public String toString() {
        return "ParticipantCreationDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isOrganiser=" + isOrganiser +
                ", isSpeaker=" + isSpeaker +
                '}';
    }
}
