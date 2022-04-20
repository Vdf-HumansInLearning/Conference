package com.vodafone.conference;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.DTO.ConferenceCreationDTO;
import com.vodafone.conference.models.entities.DTO.ConferenceDTO;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.services.ConferenceService;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;


import static io.restassured.RestAssured.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// public access modifier causes error
class ConferenceControllerTest {

    // create util class for instantiating DTOs

    @LocalServerPort
    private int port;

    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }

    @MockBean
    ConferenceService conferenceService;


    @Test
    public void givenConferenceId_whenMakingGetRequestToConferenceEndpoint_thenReturnConference() {

        UUID id = UUID.randomUUID();
        //List<Day> days = new ArrayList<>();
        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());

        //mock conference service
        // check for exception with Opt.isPresent()
        when(conferenceService.findById(id)).thenReturn(Optional.of(testConference));

        get(uri + "/conferences/" + testConference.getId()).then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                //.body("id", equalTo(testConference.getId()))
                .body("days", equalTo(testConference.getDays()))
                .body("location", equalTo(testConference.getLocation()))
                .body("theme", equalTo(testConference.getTheme()))
                .body("description", equalTo(testConference.getDescription()))
                .body("participants", equalTo(testConference.getParticipants()))
                .body("speakers", equalTo(testConference.getSpeakers()));

    }

    @Test
    public void whenMakingPostRequestToConferenceEndpoint_thenReturnResponse() {

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO();
        conferenceCreationDTO.setLocation("location");
        conferenceCreationDTO.setDescription("description");
        conferenceCreationDTO.setTheme("theme");
        conferenceCreationDTO.setDays(new ArrayList<>());
        conferenceCreationDTO.setParticipants(new ArrayList<>());
        conferenceCreationDTO.setSpeakers(new ArrayList<>());

        Response response = given().contentType("application/json")
                .body(conferenceCreationDTO)
                .post(uri + "/conferences")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        Assertions.assertEquals(List.of(), response.jsonPath().getList("days"));
        Assertions.assertEquals(List.of(), response.jsonPath().getList("participants"));
        Assertions.assertEquals(List.of(), response.jsonPath().getList("speakers"));
        Assertions.assertEquals("location", response.jsonPath().getString("location"));
        Assertions.assertEquals("theme", response.jsonPath().getString("theme"));
        Assertions.assertEquals("description", response.jsonPath().getString("description"));
    }

    @Test
    public void whenMakingPutRequestToConferenceEndpoint_thenReturnResponse() {

        //TO DO: adjust test to check for table entry replacement
        // NOTE: 1) mock findById 2) check put operation
        UUID id = UUID.randomUUID();
        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        when(conferenceService.findById(id)).thenReturn(Optional.of(testConference));

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO();
        conferenceCreationDTO.setLocation("location replace");
        conferenceCreationDTO.setDescription("description replace");
        conferenceCreationDTO.setTheme("theme replace");
        conferenceCreationDTO.setDays(new ArrayList<>());
        conferenceCreationDTO.setParticipants(new ArrayList<>());
        conferenceCreationDTO.setSpeakers(new ArrayList<>());

        Response response = given().contentType("application/json")
                .body(conferenceCreationDTO)
                .put(uri + "/conferences/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assertions.assertEquals(List.of(), response.jsonPath().getList("days"));
        Assertions.assertEquals(List.of(), response.jsonPath().getList("participants"));
        Assertions.assertEquals(List.of(), response.jsonPath().getList("speakers"));
        Assertions.assertEquals("location replace", response.jsonPath().getString("location"));
        Assertions.assertEquals("theme replace", response.jsonPath().getString("theme"));
        Assertions.assertEquals("description replace", response.jsonPath().getString("description"));
    }

    @Test
    public void whenMakingPatchRequestToConferenceEndpoint_thenReturnResponse() {

        //TO DO: adjust test to check for table entry field adjustment

        // NOTE: 1) mock findById 2) check put operation
        UUID id = UUID.randomUUID();
        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        when(conferenceService.findById(id)).thenReturn(Optional.of(testConference));

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO();
        conferenceCreationDTO.setLocation("location replace");
        conferenceCreationDTO.setDescription("description replace");
        conferenceCreationDTO.setTheme("theme replace");
        conferenceCreationDTO.setDays(new ArrayList<>());
        conferenceCreationDTO.setParticipants(new ArrayList<>());
        conferenceCreationDTO.setSpeakers(new ArrayList<>());

        Response response = given().contentType("application/json")
                .body(conferenceCreationDTO)
                .patch(uri + "/conferences/" + id)
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.OK.value(), response.statusCode());
        Assertions.assertEquals(List.of(), response.jsonPath().getList("days"));
        Assertions.assertEquals(List.of(), response.jsonPath().getList("participants"));
        Assertions.assertEquals(List.of(), response.jsonPath().getList("speakers"));
        Assertions.assertEquals("location replace", response.jsonPath().getString("location"));
        Assertions.assertEquals("theme replace", response.jsonPath().getString("theme"));
        Assertions.assertEquals("description replace", response.jsonPath().getString("description"));
    }

    @Test
    public void whenMakingDeleteRequestToConferenceEndpoint_thenReturnResponse() {

        //TO DO: adjust test to prepopulate table entry to be deleted (mock repository)
        // mockito verify

        UUID id = UUID.randomUUID();
        //Conference testConference = new Conference(id, new ArrayList<>(), "location",
        //        "theme", "description", new ArrayList<>(), new ArrayList<>());
        //when(conferenceService.deleteById(id)).thenReturn(Optional.of(testConference));

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO();
        conferenceCreationDTO.setLocation("location replace");
        conferenceCreationDTO.setDescription("description replace");
        conferenceCreationDTO.setTheme("theme replace");
        conferenceCreationDTO.setDays(new ArrayList<>());
        conferenceCreationDTO.setParticipants(new ArrayList<>());
        conferenceCreationDTO.setSpeakers(new ArrayList<>());

        Response createResponse = given().contentType("application/json")
                .body(conferenceCreationDTO)
                .put(uri + "/conferences/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.OK.value(), createResponse.statusCode());

        Response deleteResponse = given().contentType("application/json")
                //.body(conferenceCreationDTO)
                .delete(uri + "/conferences/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value())
                .extract()
                .response();

        //Assertions.assertEquals(null, deleteResponse.getBody());

        //Response getResponse = (Response) get(uri + "/conferences/" + id).then()
        //        .assertThat()
        //        .statusCode(HttpStatus.OK.value())
        //                .body("", equalTo(nullValue()));

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), deleteResponse.statusCode());



    }
}
