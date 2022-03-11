package com.vodafone.conference.models.entities.DTO;

public class ParticipantDTO {
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String phoneNumber;
    private String username;

    public ParticipantDTO(String firstName, String lastName, String title, String email, String phoneNumber, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
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
}
