package com.vodafone.conference;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.DTO.ParticipantCreationDTO;
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
import java.util.*;

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

        //UUID id = UUID.randomUUID();
        Participant testParticipant = new Participant("firstName", "lastName", "title", "email",
                "phoneNumber", "username", "password", null, null, false, false, null);

        System.out.println(testParticipant.getIsOrganiser() + " " + testParticipant.getIsSpeaker());

        when(participantService.findById(testParticipant.getId())).thenReturn(Optional.of(testParticipant));

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

    //TO DO: find out how to iterate through JSON answer to check participants
    @Test
    public void givenConferenceId_whenMakingGetRequestToParticipantsEndpoint_thenReturnParticipant() {

        //UUID id = UUID.randomUUID();
        //UUID id1 = UUID.randomUUID();
        //UUID id2 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Participant testParticipant1 = new Participant("firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, null, true, true, testConference);

        Participant testParticipant2 = new Participant("firstName2", "lastName2", "title2", "email2",
                "phoneNumber2", "username2", "password2", null, null, false, false, testConference);

        List<Participant> participants = new ArrayList<>();
        participants.add(testParticipant1);
        participants.add(testParticipant2);

        //ParticipantMapper participantMapper = new ParticipantMapper();
        //List<ParticipantDTO> participantDTOS = new ArrayList<>();
        //participantDTOS.add(participantMapper.toDto(testParticipant1));
        //participantDTOS.add(participantMapper.toDto(testParticipant2));

        when(conferenceService.findById(testConference.getId())).thenReturn(Optional.of(testConference));
        when(participantService.findByConference_Id(testConference.getId())).thenReturn(participants);

        Response response =  get(uri + "/conferences/" + testConference.getId() + "/participants").then()
                .extract().response();

        List responseList = response.as(List.class);
        System.out.println(responseList);
        //System.out.println(participantDTOS);
        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());
        //Assertions.assertEquals(response, response);

    }

    @Test
    public void whenMakingPostRequestToParticipantsEndpoint_thenReturnResponse() {

        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(conferenceService.findById(testConference.getId())).thenReturn(Optional.of(testConference));

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

        //UUID id = UUID.randomUUID();
        //UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Participant testParticipant1 = new Participant( "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null,null, true, true, testConference);
        when(conferenceService.findById(testConference.getId())).thenReturn(Optional.of(testConference));

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
                .put(uri + "/participants/" + testParticipant1.getId())
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

    @Test
    public void whenMakingPatchRequestToConferenceEndpoint_thenReturnResponse() {

        //UUID id = UUID.randomUUID();
        //UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Participant testParticipant1 = new Participant( "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, null, true, true, testConference);

        when(participantService.findById(testParticipant1.getId())).thenReturn(Optional.of(testParticipant1));

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
                .patch(uri + "/participants/" + testParticipant1.getId())
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assertions.assertEquals("firstName", response.jsonPath().getString("firstName"));
        Assertions.assertEquals("lastName", response.jsonPath().getString("lastName"));
        Assertions.assertEquals("title", response.jsonPath().getString("title"));
        Assertions.assertEquals("email", response.jsonPath().getString("email"));
        Assertions.assertEquals("phoneNumber", response.jsonPath().getString("phoneNumber"));
    }

    @Test
    public void whenMakingDeleteRequestToParticipantsEndpoint_thenReturnResponse() {

        //TO DO: adjust test to prepopulate table entry to be deleted (mock)
        // mockito verify

        //UUID id = UUID.randomUUID();
        //UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        Participant testParticipant1 = new Participant( "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, null, true, true, testConference);
        when(conferenceService.findById(testParticipant1.getId())).thenReturn(Optional.of(testConference));

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

        Response createResponse = given().contentType("application/json")
                .body(participantCreationDTO)
                .put(uri + "/participants/" + testParticipant1.getId())
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.OK.value(), createResponse.statusCode());

        Response deleteResponse = given().contentType("application/json")
                //.body(conferenceCreationDTO)
                .delete(uri + "/participants/" + testParticipant1.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .extract()
                .response();

        //System.out.println("Printing response: " + deleteResponse.asString());

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), deleteResponse.statusCode());
    }
}
