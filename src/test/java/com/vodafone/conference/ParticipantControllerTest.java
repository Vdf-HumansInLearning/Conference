package com.vodafone.conference;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.DTO.ParticipantCreationDTO;
import com.vodafone.conference.models.entities.DTO.ParticipantDTO;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.services.ConferenceService;
import com.vodafone.conference.services.ParticipantService;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public access modifier causes error
class ParticipantControllerTest {

    @LocalServerPort
    private int port;

    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }

    @MockBean
    ParticipantService participantService;
    @MockBean
    ConferenceService conferenceService;


    @Test
    public void givenParticipantId_whenMakingGetRequestToParticipantEndpoint_thenReturnParticipant() {

        UUID id = UUID.randomUUID();

        Participant testParticipant = new Participant(id, "firstName", "lastName", "title", "email",
                "phoneNumber", "username", "password", null, false, false, null);

        System.out.println(testParticipant.getIsOrganiser() + " " + testParticipant.getIsSpeaker());

        when(participantService.findById(id)).thenReturn(Optional.of(testParticipant));

        get(uri + "/participants/" + testParticipant.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                //.body("id", equalTo(testConference.getId()))
                .body("firstName", equalTo(testParticipant.getFirstName()))
                .body("lastName", equalTo(testParticipant.getLastName()))
                .body("title", equalTo(testParticipant.getTitle()))
                .body("email", equalTo(testParticipant.getEmail()))
                .body("phoneNumber", equalTo(testParticipant.getPhoneNumber()))
                .body("username", equalTo(testParticipant.getUsername()))

                //.body("isOrganiser", equalTo(testParticipant.getIsOrganiser()))
                //.body("isSpeaker", equalTo(testParticipant.getIsSpeaker()));

                // JSON field names do not match DTO field names
                .body("organiser", equalTo(testParticipant.getIsOrganiser()))
                .body("speaker", equalTo(testParticipant.getIsSpeaker()));
    }

    // TO DO: fix test
    /*@Test
    public void givenConferenceId_whenMakingGetRequestToParticipantsEndpoint_thenReturnParticipant() {

        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());

        Participant testParticipant1 = new Participant(id1, "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, true, true, testConference);

        Participant testParticipant2 = new Participant(id2, "firstName2", "lastName2", "title2", "email2",
                "phoneNumber2", "username2", "password2", null, false, false, testConference);

        Response response =  get(uri + "/conferences/" + testConference.getId() + "/participants").then()
                .extract().response();

        System.out.println(response.asString());
        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());

        //TO DO: find out how to iterate through JSON answer to check participants
    }*/

    @Test
    public void whenMakingPostRequestToParticipantsEndpoint_thenReturnResponse() {

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

        System.out.println(participantCreationDTO.toString());

        Response response = given().contentType("application/json")
                .body(participantCreationDTO)
                .post(uri + "/conferences/" + testConference.getId() + "/participants")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        Assertions.assertEquals("firstName", response.jsonPath().getString("firstName"));
        Assertions.assertEquals("lastName", response.jsonPath().getString("lastName"));
        Assertions.assertEquals("title", response.jsonPath().getString("title"));
        Assertions.assertEquals("email", response.jsonPath().getString("email"));
        Assertions.assertEquals("phoneNumber", response.jsonPath().getString("phoneNumber"));

        // TO DO: fix the errors for following assertions
        // JSON field names do not match DTO field names
        //Assertions.assertEquals(true, response.jsonPath().getBoolean("isOrganiser"));
        //Assertions.assertEquals(true, response.jsonPath().getBoolean("isSpeaker"));
    }

    @Test
    public void whenMakingPutRequestToParticipantsEndpoint_thenReturnResponse() {

        UUID id = UUID.randomUUID();
        //UUID id1 = UUID.randomUUID();

        //Conference testConference = new Conference(id, new ArrayList<>(), "location",
        //        "theme", "description", new ArrayList<>(), new ArrayList<>());

        //Participant testParticipant1 = new Participant(id1, "firstName1", "lastName1", "title1", "email1",
        //        "phoneNumber1", "username1", "password1", null, true, true, testConference);

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

        //System.out.println(participantCreationDTO.toString());

        Response response = given().contentType("application/json")
                .body(participantCreationDTO)
                .put(uri + "/participants/" + id)
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assertions.assertEquals("firstName", response.jsonPath().getString("firstName"));
        Assertions.assertEquals("lastName", response.jsonPath().getString("lastName"));
        Assertions.assertEquals("title", response.jsonPath().getString("title"));
        Assertions.assertEquals("email", response.jsonPath().getString("email"));
        Assertions.assertEquals("phoneNumber", response.jsonPath().getString("phoneNumber"));

        // TO DO: fix the errors for following assertions
        //Assertions.assertEquals(true, response.jsonPath().getBoolean("isOrganiser"));
        //Assertions.assertEquals(true, response.jsonPath().getBoolean("isSpeaker"));
    }

    // TO DO: fix test
    /*@Test
    public void whenMakingPatchRequestToConferenceEndpoint_thenReturnResponse() {

        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

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

        Response response = given().contentType("application/json")
                .body(participantCreationDTO)
                .patch(uri + "/participants/" + id1)
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

        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());

        Participant testParticipant1 = new Participant(id1, "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, true, true, testConference);

        Response response = given().contentType("application/json")
                //.body(conferenceCreationDTO)
                .delete(uri + "/participants/" + id1)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), response.statusCode());
    }
}
