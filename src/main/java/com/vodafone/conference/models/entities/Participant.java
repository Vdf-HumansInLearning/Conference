package com.vodafone.conference.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participant {

    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

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

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session sessions;

    @OneToMany(mappedBy = "participant")
    private List<Ticket> tickets;

    @Column(name = "is_organiser", columnDefinition = "tinyint(1) default 0")
    private Boolean isOrganiser;

    @Column(name = "is_speaker", columnDefinition = "tinyint(1) default 0")
    private Boolean isSpeaker;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    @OneToMany(mappedBy = "participant")
    private List<Track> tracks;
}