package com.vodafone.conference;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.services.ConferenceService;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConferenceControllerTest {

    /*@LocalServerPort
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
                "theme", "description", new ArrayList<>());

        when(conferenceService.findById(id)).thenReturn(Optional.of(testConference));


    }*/
}
