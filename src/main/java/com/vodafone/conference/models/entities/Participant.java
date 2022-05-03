package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participant")
public class Participant extends EntityWithUUID {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "title", nullable = false)
    private String title;

    @Email(message = "Email should be valid!")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Range(min = 10,max = 13, message = "Phone number not valid!" )
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "participant")
    private Speaker speaker;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "session_id", nullable = false)
    private Session sessions;

    @OneToMany(mappedBy = "participant") @JsonIgnore
    private List<Ticket> tickets;

    @Column(name = "is_organiser", columnDefinition = "boolean")
    private Boolean isOrganiser;

    @Column(name = "is_speaker", columnDefinition = "boolean")
    private Boolean isSpeaker;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;
}
