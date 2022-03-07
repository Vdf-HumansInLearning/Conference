package com.vodafone.conference.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;
import java.util.List;
import javax.validation.constraints.*;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class    Participant {

    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z]\\w{1,49}$", message = " First name must be between 2 and 50 characters and use upper and lowercase characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z]\\w{1,49}$", message = " Last name must be between 2 and 50 characters and use upper and lowercase characters")
    private String lastName;

    @Column(name = "title", nullable = false)

    private String title;

    @Email()
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Must conform to OWASP email standard")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    //@Range(min = 10,max = 13, message = "Must be a valid phone number!" )
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")
    //"^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$|^(\\\\+\\\\d{1,3}( )?)?(\\\\d{3}[ ]?){2}\\\\d{3}$|^(\\\\+\\\\d{1,3}( )?)?(\\\\d{3}[ ]?)(\\\\d{2}[ ]?){2}\\\\d{2}$"
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[A-Za-z]\\w{1,49}$", message = " Username must be between 2 and 50 characters and use upper and lowercase characters")
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password is required")
    /*
    * A password is considered valid if all the following constraints are satisfied:

    It contains at least 8 characters and at most 20 characters.
    It contains at least one digit.
    It contains at least one upper case alphabet.
    It contains at least one lower case alphabet.
    It contains at least one special character which includes !@#$%&*()-+=^.
    It does not contain any white space.*/
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8, 20}$")
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