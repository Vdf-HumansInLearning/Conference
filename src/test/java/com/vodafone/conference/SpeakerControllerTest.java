package com.vodafone.conference;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.DTO.ParticipantCreationDTO;
import com.vodafone.conference.models.entities.DTO.SpeakerCreationDTO;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Speaker;
import com.vodafone.conference.services.ConferenceService;
import com.vodafone.conference.services.ParticipantService;
import com.vodafone.conference.services.SpeakerService;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public access modifier causes error
class SpeakerControllerTest {

    @LocalServerPort
    private int port;

    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }

    @MockBean
    SpeakerService speakerService;
    @MockBean
    ParticipantService participantService;
    @MockBean
    ConferenceService conferenceService;


    @Test
    public void givenSpeakerId_whenMakingGetRequestToSpeakerEndpoint_thenReturnSpeaker() {

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();

        Conference testConference = new Conference(id1, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());

        Participant testParticipant = new Participant(id2, "firstName", "lastName", "title", "email",
                "phoneNumber", "username", "password", null, false, false, null);

        //Speaker testSpeaker = new Speaker(id3, testParticipant, "title", "company",
        //        "linkedinAcc", "twitterAcc", "githubAcc",
        //        "biography", testConference);

        Speaker testSpeaker = new Speaker(id3, testParticipant, "company",
                "linkedinAcc", "twitterAcc", "githubAcc",
                "biography", testConference);

        when(speakerService.findById(id3)).thenReturn(Optional.of(testSpeaker));

        get(uri + "/speakers/" + testSpeaker.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                // TO DO: check participantDTO details against testParticipant details
                //.body()
                .body("company", equalTo(testSpeaker.getCompany()))
                .body("linkedinAcc", equalTo(testSpeaker.getLinkedinAcc()))
                .body("twitterAcc", equalTo(testSpeaker.getTwitterAcc()))
                .body("githubAcc", equalTo(testSpeaker.getGithubAcc()))
                .body("biography", equalTo(testSpeaker.getBiography()));
    }

    // TO DO: fix test
    /*@Test
    public void givenConferenceId_whenMakingGetRequestToSpeakersEndpoint_thenReturnSpeaker() {

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id4 = UUID.randomUUID();
        UUID id5 = UUID.randomUUID();

        Conference testConference = new Conference(id1, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());

        Participant testParticipant1 = new Participant(id2, "firstName", "lastName", "title", "email",
                "phoneNumber", "username", "password", null, false, false, testConference);

        Participant testParticipant2 = new Participant(id3, "firstName2", "lastName2", "title2", "email2",
                "phoneNumber2", "username2", "password2", null, false, false, testConference);

        Speaker testSpeaker = new Speaker(id4, testParticipant1, "title", "company",
                "linkedinAcc", "twitterAcc", "githubAcc",
                "biography", testConference);

        Speaker testSpeaker2 = new Speaker(id5, testParticipant2, "title", "company",
                "linkedinAcc", "twitterAcc", "githubAcc",
                "biography", testConference);

        Response response =  get(uri + "/conferences/" + testConference.getId() + "/speakers").then()
                .extract().response();

        //System.out.println(response.asString());
        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());

        //TO DO: find out how to iterate through JSON answer to check participants
    }*/

    @Test
    public void whenMakingPostRequestToSpeakersEndpoint_thenReturnResponse() {

        UUID id = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());

        when(conferenceService.findById(id)).thenReturn(Optional.of(testConference));

        ParticipantCreationDTO participantCreationDTO = new ParticipantCreationDTO();
        participantCreationDTO.setFirstName("firstName");
        participantCreationDTO.setLastName("lastName");
        participantCreationDTO.setTitle("title");
        participantCreationDTO.setEmail("email");
        participantCreationDTO.setPhoneNumber("phoneNumber");
        participantCreationDTO.setUsername("username");
        participantCreationDTO.setPassword("password");
        participantCreationDTO.setOrganiser(true);
        participantCreationDTO.setSpeaker(true);

        SpeakerCreationDTO speakerCreationDTO = new SpeakerCreationDTO();
        speakerCreationDTO.setParticipantCreationDTO(participantCreationDTO);
        speakerCreationDTO.setCompany("company");
        speakerCreationDTO.setLinkedinAcc("linkedinAcc");
        speakerCreationDTO.setGithubAcc("githubAcc");
        speakerCreationDTO.setTwitterAcc("twitterAcc");
        speakerCreationDTO.setBiography("biography");

        Response response = given().contentType("application/json")
                .body(speakerCreationDTO)
                .post(uri + "/conferences/" + testConference.getId() + "/speakers")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        // Might have to test participantDTO fields as well

        Assertions.assertEquals("company", response.jsonPath().getString("company"));
        Assertions.assertEquals("linkedinAcc", response.jsonPath().getString("linkedinAcc"));
        Assertions.assertEquals("githubAcc", response.jsonPath().getString("githubAcc"));
        Assertions.assertEquals("twitterAcc", response.jsonPath().getString("twitterAcc"));
        Assertions.assertEquals("biography", response.jsonPath().getString("biography"));
    }

    @Test
    public void whenMakingPutRequestToSpeakersEndpoint_thenReturnResponse() {

        UUID id = UUID.randomUUID();

        //Conference testConference = new Conference(id, new ArrayList<>(), "location",
        //        "theme", "description", new ArrayList<>(), new ArrayList<>());

        //when(conferenceService.findById(id)).thenReturn(Optional.of(testConference));

        ParticipantCreationDTO participantCreationDTO = new ParticipantCreationDTO();
        participantCreationDTO.setFirstName("firstName");
        participantCreationDTO.setLastName("lastName");
        participantCreationDTO.setTitle("title");
        participantCreationDTO.setEmail("email");
        participantCreationDTO.setPhoneNumber("phoneNumber");
        participantCreationDTO.setUsername("username");
        participantCreationDTO.setPassword("password");
        participantCreationDTO.setOrganiser(true);
        participantCreationDTO.setSpeaker(true);

        SpeakerCreationDTO speakerCreationDTO = new SpeakerCreationDTO();
        speakerCreationDTO.setParticipantCreationDTO(participantCreationDTO);
        speakerCreationDTO.setCompany("company");
        speakerCreationDTO.setLinkedinAcc("linkedinAcc");
        speakerCreationDTO.setGithubAcc("githubAcc");
        speakerCreationDTO.setTwitterAcc("twitterAcc");
        speakerCreationDTO.setBiography("biography");

        Response response = given().contentType("application/json")
                .body(speakerCreationDTO)
                .put(uri + "/speakers/" + id)
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());

        // Might have to test participantDTO fields as well

        Assertions.assertEquals("company", response.jsonPath().getString("company"));
        Assertions.assertEquals("linkedinAcc", response.jsonPath().getString("linkedinAcc"));
        Assertions.assertEquals("githubAcc", response.jsonPath().getString("githubAcc"));
        Assertions.assertEquals("twitterAcc", response.jsonPath().getString("twitterAcc"));
        Assertions.assertEquals("biography", response.jsonPath().getString("biography"));

    }

    // TO DO: fix test
    /*@Test
    public void whenMakingPatchRequestToConferenceEndpoint_thenReturnResponse() {

        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        //when(speakerService.findById(id)).thenReturn(Optional.of(testSpeaker));

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());

        Participant testParticipant1 = new Participant(id1, "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, true, true, testConference);

        ParticipantCreationDTO participantCreationDTO = new ParticipantCreationDTO();
        participantCreationDTO.setFirstName("firstName");
        participantCreationDTO.setLastName("lastName");
        participantCreationDTO.setTitle("title");
        participantCreationDTO.setEmail("email");
        participantCreationDTO.setPhoneNumber("phoneNumber");
        participantCreationDTO.setUsername("username");
        participantCreationDTO.setPassword("password");
        participantCreationDTO.setOrganiser(true);
        participantCreationDTO.setSpeaker(true);

        SpeakerCreationDTO speakerCreationDTO = new SpeakerCreationDTO();
        speakerCreationDTO.setParticipantCreationDTO(participantCreationDTO);
        speakerCreationDTO.setCompany("company");
        speakerCreationDTO.setLinkedinAcc("linkedinAcc");
        speakerCreationDTO.setGithubAcc("githubAcc");
        speakerCreationDTO.setTwitterAcc("twitterAcc");
        speakerCreationDTO.setBiography("biography");

        Response response = given().contentType("application/json")
                .body(speakerCreationDTO)
                .patch(uri + "/speakers/" + id1)
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assertions.assertEquals("firstName", response.jsonPath().getString("firstName"));
        Assertions.assertEquals("lastName", response.jsonPath().getString("lastName"));
        Assertions.assertEquals("title", response.jsonPath().getString("title"));
        Assertions.assertEquals("email", response.jsonPath().getString("email"));
        Assertions.assertEquals("phoneNumber", response.jsonPath().getString("phoneNumber"));
    }*/

    @Test
    public void whenMakingDeleteRequestToParticipantsEndpoint_thenReturnResponse() {

        //TO DO: adjust test to prepopulate table entry to be deleted (mock)

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();

        Conference testConference = new Conference(id1, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());

        Participant testParticipant = new Participant(id2, "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, true, true, testConference);

        Speaker testSpeaker = new Speaker(id3, testParticipant, "company",
                "linkedinAcc", "twitterAcc", "githubAcc",
                "biography", testConference);

        Response response = given().contentType("application/json")
                //.body(conferenceCreationDTO)
                .delete(uri + "/speakers/" + id3)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.statusCode());
    }
}
