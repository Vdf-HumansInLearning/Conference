package com.vodafone.conference.api.controllers;

import com.vodafone.conference.models.dto.ConferenceCreationDTO;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class ConferenceControllerTest {

    Response insertConferenceInDB() {

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO();
        conferenceCreationDTO.setLocation("Location Test");
        conferenceCreationDTO.setTheme("Theme Test");
        conferenceCreationDTO.setDescription("Description Test");

        return given().contentType("application/json")
                .body(conferenceCreationDTO)
                .post("conferences")
                .then().extract().response();
    }

    @Test
    void getConferences() {
        Response response = insertConferenceInDB();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        Response getResponse = given().contentType("application/json")
                .get("/conferences")
                .then().extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), getResponse.statusCode());
    }

    @Test
    void getConferenceById() {
        Response response = insertConferenceInDB();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        String id = new JsonPath(response.getBody().asString()).getString("id");

        Response getResponse = given().contentType("application/json")
                .get("/conferences/" + id)
                .then().extract().response();
        Assertions.assertEquals(HttpStatus.OK.value(), getResponse.statusCode());
        Assertions.assertEquals("Location Test", response.jsonPath().getString("location"));
        Assertions.assertEquals("Theme Test", response.jsonPath().getString("theme"));
        Assertions.assertEquals("Description Test", response.jsonPath().getString("description"));
    }

    @Test
    void createConference() {
        Response response = insertConferenceInDB();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        Assertions.assertEquals("Location Test", response.jsonPath().getString("location"));
        Assertions.assertEquals("Theme Test", response.jsonPath().getString("theme"));
        Assertions.assertEquals("Description Test", response.jsonPath().getString("description"));
    }

    @Test
    void putConferenceById() {
        Response response = insertConferenceInDB();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        String id = new JsonPath(response.getBody().asString()).getString("id");

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO();
        conferenceCreationDTO.setLocation("Put Location Test");
        conferenceCreationDTO.setTheme("Put Theme Test");
        conferenceCreationDTO.setDescription("Put Description Test");

        Response putResponse = given().contentType("application/json")
                .body(conferenceCreationDTO)
                .put("/conferences/" + id)
                .then().extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), putResponse.statusCode());
        Assertions.assertEquals("Put Location Test", putResponse.jsonPath().getString("location"));
        Assertions.assertEquals("Put Theme Test", putResponse.jsonPath().getString("theme"));
        Assertions.assertEquals("Put Description Test", putResponse.jsonPath().getString("description"));
    }

    @Test
    void deleteConference() {
        Response response = insertConferenceInDB();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        String id = new JsonPath(response.getBody().asString()).getString("id");

        Response deleteResponse = given().contentType("application/json")
                .delete("/conferences/" + id)
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), deleteResponse.statusCode());
    }
}
