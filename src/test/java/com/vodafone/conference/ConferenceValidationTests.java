package com.vodafone.conference;

import com.vodafone.conference.models.entities.Conference;
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

public class ConferenceValidationTests {


    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void whenNullLocation_thenConstraintViolation() {
        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Set<ConstraintViolation<Conference>> violations = validator.validate(testConference);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyLocation_thenConstraintViolation() {
        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Set<ConstraintViolation<Conference>> violations = validator.validate(testConference);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenNullTheme_thenConstraintViolation() {
        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Set<ConstraintViolation<Conference>> violations = validator.validate(testConference);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyTheme_thenConstraintViolation() {
        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Set<ConstraintViolation<Conference>> violations = validator.validate(testConference);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenNullDescription_thenConstraintViolation() {
        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Set<ConstraintViolation<Conference>> violations = validator.validate(testConference);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyDescription_thenConstraintViolation() {
        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Set<ConstraintViolation<Conference>> violations = validator.validate(testConference);
        assertThat(violations.size()).isEqualTo(1);
    }
}
