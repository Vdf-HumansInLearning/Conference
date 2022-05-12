package com.vodafone.conference;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Speaker;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SpeakerValidationTests {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void whenNullCompany_thenConstraintViolation() {
        //UUID id1 = UUID.randomUUID();
        //UUID id2 = UUID.randomUUID();
        //UUID id3 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant( null, "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "vluta", "password", null, null, true, true, testConference);
        Speaker testSpeaker = new Speaker( testParticipant, null,
                "linkedinAcc", "twitterAcc", "githubAcc",
                "biography", new HashSet<>(), testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyCompany_thenConstraintViolation() {
        //UUID id1 = UUID.randomUUID();
        //UUID id2 = UUID.randomUUID();
        //UUID id3 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant( "Vlad", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "a", "password", null, null, true, true, testConference);
        Speaker testSpeaker = new Speaker( testParticipant, "",
                "linkedinAcc", "twitterAcc", "githubAcc",
                "biography", new HashSet<>(), testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }



    @Test
    public void whenNullBiography_thenConstraintViolation() {
        //UUID id1 = UUID.randomUUID();
        //UUID id2 = UUID.randomUUID();
        //UUID id3 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant( "Vlad", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "a", "password", null, null, true, true, testConference);
        Speaker testSpeaker = new Speaker( testParticipant, "company",
                "linkedinAcc", "twitterAcc", "githubAcc",
                null, new HashSet<>(), testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyBiography_thenConstraintViolation() {
        //UUID id1 = UUID.randomUUID();
        //UUID id2 = UUID.randomUUID();
        //UUID id3 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant( "Vlad", "Luta", "Mr", "vluta@hotmail.com",
                "0730257597", "a", "password", null, null, true, true, testConference);
        Speaker testSpeaker = new Speaker( testParticipant, "company",
                "linkedinAcc", "twitterAcc", "githubAcc",
                "", new HashSet<>(), testConference);

        Set<ConstraintViolation<Participant>> violations = validator.validate(testParticipant);
        assertThat(violations.size()).isEqualTo(1);
    }
}
