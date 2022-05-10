package com.vodafone.conference.api.controllers;

import com.vodafone.conference.models.dto.SessionCreationDTO;
import com.vodafone.conference.models.dto.SessionTypeCreationDTO;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;


class SessionControllerTest {

    Response insertSessionInDB() {
        //TODO: Adauga un track si extrage ID
        String trackId = "2edd0b57-1a55-4fa5-81ce-1d1bc8a52aa8";

        SessionTypeCreationDTO sessionTypeCreationDTO = new SessionTypeCreationDTO();
        sessionTypeCreationDTO.setType("Test");
        sessionTypeCreationDTO.setSessionLength(999);

        SessionCreationDTO sessionCreationDTO = new SessionCreationDTO();
        sessionCreationDTO.setTitle("Session Test");
        sessionCreationDTO.setDescription("Description Test");
        sessionCreationDTO.setSessionTypeCreationDTO(sessionTypeCreationDTO);
        sessionCreationDTO.setTopic("Topic Test");
        sessionCreationDTO.setTechLevel("Tech Level Test");
        List<String> keywards = Arrays.asList("keyword 1", "keyword 2", "keyword 3");
        sessionCreationDTO.setKeywords(keywards);
        sessionCreationDTO.setDate(LocalDate.now());
        sessionCreationDTO.setEndTime(LocalDate.now());

        return given().contentType("application/json")
                .body(sessionCreationDTO)
                .post("tracks/" + trackId + "/sessions")
                .then().extract().response();
    }

    @Test
    void createSession() {
        Response response = insertSessionInDB();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        Assertions.assertEquals("Session Test", response.jsonPath().getString("title"));
        Assertions.assertNull(response.jsonPath().getString("speakers"));
        Assertions.assertEquals("Description Test", response.jsonPath().getString("description"));
        Assertions.assertEquals("Topic Test", response.jsonPath().getString("topic"));
        Assertions.assertEquals("Tech Level Test", response.jsonPath().getString("techLevel"));
        Assertions.assertEquals(0, response.jsonPath().getInt("review"));
        Assertions.assertNull(response.jsonPath().getString("participants"));
    }

    @Test
    void getSessions() {
        Response response = insertSessionInDB();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        Response getResponse = given().contentType("application/json")
                .get("/sessions")
                .then().extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), getResponse.statusCode());
    }

    @Test
    void getSessionById() {
        Response response = insertSessionInDB();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        String id = new JsonPath(response.getBody().asString()).getString("id");

        Response getResponse = given().contentType("application/json")
                .get("/sessions/" + id)
                .then().extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), getResponse.statusCode());
        Assertions.assertEquals("Session Test", response.jsonPath().getString("title"));
        Assertions.assertNull(response.jsonPath().getString("speakers"));
        Assertions.assertEquals("Description Test", response.jsonPath().getString("description"));
        Assertions.assertEquals("Topic Test", response.jsonPath().getString("topic"));
        Assertions.assertEquals("Tech Level Test", response.jsonPath().getString("techLevel"));
        Assertions.assertEquals(0, response.jsonPath().getInt("review"));
        Assertions.assertNull(response.jsonPath().getString("participants"));
    }

    @Test
    void putSessionById() {
        Response response = insertSessionInDB();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        String id = new JsonPath(response.getBody().asString()).getString("id");

        SessionTypeCreationDTO sessionTypeCreationDTO = new SessionTypeCreationDTO();
        sessionTypeCreationDTO.setType("Test");
        sessionTypeCreationDTO.setSessionLength(999);

        SessionCreationDTO sessionCreationDTOforUpdate = new SessionCreationDTO(
                "PUT Title", "PUT Description", sessionTypeCreationDTO, "PUT Topic", "PUT TechLevel",
                List.of("Keyword1", "Keyword2"), LocalDate.now(), LocalDate.now()
        );

        Response putResponse = given().contentType("application/json")
                .body(sessionCreationDTOforUpdate)
                .put("/sessions/" + id)
                .then().extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), putResponse.statusCode());
        Assertions.assertEquals("PUT Title", putResponse.jsonPath().getString("title"));
        Assertions.assertNull(putResponse.jsonPath().getString("speakers"));
        Assertions.assertEquals("PUT Description", putResponse.jsonPath().getString("description"));
        Assertions.assertEquals("PUT Topic", putResponse.jsonPath().getString("topic"));
        Assertions.assertEquals("PUT TechLevel", putResponse.jsonPath().getString("techLevel"));
        Assertions.assertEquals(0, putResponse.jsonPath().getInt("review"));
        Assertions.assertNull(putResponse.jsonPath().getString("participants"));
    }

    @Test
    void deleteSession() {
        Response response = insertSessionInDB();
        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        String id = new JsonPath(response.getBody().asString()).getString("id");

        Response deleteResponse = given().contentType("application/json")
                .delete("/sessions/" + id)
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), deleteResponse.statusCode());
    }
}