package com.vodafone.conference.api.controllers;

import com.vodafone.conference.models.dto.TicketCreationDTO;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class TicketControllerTest {

    Response insertTicketInDB() {

        TicketCreationDTO ticketCreationDTO = new TicketCreationDTO("Test" ,30);

        return given().contentType("application/json")
                .body(ticketCreationDTO)
                .post("tickets")
                .then().extract().response();
    }

    @Test
    void getTickets() {
        Response response = insertTicketInDB();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        Response getResponse = given().contentType("application/json")
                .get("/tickets")
                .then().extract().response();

        Assertions.assertEquals(HttpStatus.OK.value(), getResponse.statusCode());
    }

    @Test
    void getTicketById() {
        Response response = insertTicketInDB();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        String id = new JsonPath(response.getBody().asString()).getString("id");

        Response getResponse = given().contentType("application/json")
                .get("/tickets/" + id)
                .then().extract().response();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());
        Assertions.assertEquals("Test", getResponse.jsonPath().getString("type"));
        Assertions.assertEquals(30, getResponse.jsonPath().getInt("price"));
    }

    @Test
    void createTikcet() {
        Response response = insertTicketInDB();

        Assertions.assertEquals(HttpStatus.CREATED.value(), response.statusCode());

        Assertions.assertEquals("Test", response.jsonPath().getString("type"));
        Assertions.assertEquals(30, response.jsonPath().getInt("price"));
    }
}
