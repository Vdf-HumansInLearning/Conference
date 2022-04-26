package com.vodafone.conference;

import com.vodafone.conference.models.entities.DTO.ParticipantCreationDTO;
import com.vodafone.conference.services.ParticipantService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConferenceApplicationTests {

    @Test
    void contextLoads() {
    }

    /*
    // Server and run info
    @LocalServerPort
    private int port = 8081;

    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }

    // Participant tests
    @MockBean
    ParticipantService participantService;

    @Test
    public void givenParticipantId_whenMakingGetRequestToParticipantEndpoint_thenReturnParticipants() {
        ParticipantCreationDTO participantCreationDTO = new ParticipantCreationDTO();
    }*/
}
