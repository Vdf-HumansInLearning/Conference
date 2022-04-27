package com.vodafone.conference.api.controllers;

import com.vodafone.conference.models.dto.SessionTypeCreationDTO;

import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

class SessionTypeControllerTest {

    Response insertSessionTypeInDB() {
        SessionTypeCreationDTO sessionTypeCreationDTO = new SessionTypeCreationDTO();
        sessionTypeCreationDTO.setType("Test");
        sessionTypeCreationDTO.setSessionLength(999);

        return given().contentType("application/json")
                .body(sessionTypeCreationDTO)
                .post("/session_types")
                .then().extract().response();
    }

    @Test
    void createSessionType() {
        Response response = insertSessionTypeInDB();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        Assertions.assertEquals("Test", response.jsonPath().getString("type"));
        Assertions.assertEquals(999, response.jsonPath().getInt("sessionLength"));
    }

    @Test
    void getSessionType() {
        Response response = insertSessionTypeInDB();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        Response getResponse = given().contentType("application/json")
                .get("/session_types")
                .then().extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), getResponse.statusCode());
    }

    @Test
    void getSessionTypeById() {
        Response response = insertSessionTypeInDB();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        String id = new JsonPath(response.getBody().asString()).getString("id");

        Response getResponse = given().contentType("application/json")
                .get("/session_types/" + id)
                .then().extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), getResponse.statusCode());
        Assertions.assertEquals("Test", getResponse.jsonPath().getString("type"));
        Assertions.assertEquals(999, getResponse.jsonPath().getInt("sessionLength"));
    }

    @Test
    void putSessionTypeById() {
        Response response = insertSessionTypeInDB();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        String id = new JsonPath(response.getBody().asString()).getString("id");

        SessionTypeCreationDTO sessionTypeCreationDTOforUpdate = new SessionTypeCreationDTO("PUT Test", 200);

        Response putResponse = given().contentType("application/json")
                .body(sessionTypeCreationDTOforUpdate)
                .put("/session_types/" + id)
                .then().extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), putResponse.statusCode());
        Assertions.assertEquals("PUT Test", putResponse.jsonPath().getString("type"));
        Assertions.assertEquals(200, putResponse.jsonPath().getInt("sessionLength"));
    }

    @Test
    void deleteSessionType() {
        Response response = insertSessionTypeInDB();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        String id = new JsonPath(response.getBody().asString()).getString("id");

        Response deleteResponse = given().contentType("application/json")
                .delete("/session_types/" + id)
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), deleteResponse.statusCode());
    }
}
