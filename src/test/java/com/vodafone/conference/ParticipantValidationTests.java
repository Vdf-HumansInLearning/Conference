package com.vodafone.conference;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.locks.StampedLock;

import static org.assertj.core.api.Assertions.assertThat;

public class ParticipantValidationTests {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void whenNullFirstName_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, null, "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "vluta", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyFirstName_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "vluta", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    public void whenWrongFirstName_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "a", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "vluta", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }


    @Test
    public void whenNullLastName_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", null, "Mr", "vluta@hotmail.com",
                "0730257597", "vluta", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyLastName_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "", "Mr", "vluta@hotmail.com",
                "0730257597", "vluta", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    public void whenNullEmail_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "Luta", "Mr", null,
                "0730257597", "vluta", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyEmail_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "Luta", "Mr", "",
                "0730257597", "vluta", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenWrongLastName_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "a", "Mr", "vluta@hotmail.com",
                "0730257597", "vluta", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }


    @Test
    public void whenWrongPhoneNumber_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "Luta", "Mr", "vluta@hotmail.com",
                "12345", "vluta", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }


    @Test
    public void whenNullUsername_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", null, "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyUsername_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    public void whenWrongUsername_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "a", "password", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }


    @Test
    public void whenNullPassword_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "username", null, null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyPassword_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "username", "", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    public void whenWrongPassword_thenConstraintViolation() {
        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant(id1, "Vlad", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "username", "a", null, true, true, testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }
}
