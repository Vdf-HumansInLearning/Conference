package com.vodafone.conference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.DTO.ParticipantCreationDTO;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.services.ParticipantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ ConferenceApplication.class })
public class ParticipantIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String uri = "http://localhost:5433";

    @MockBean
    private ParticipantService participantService;

    @MockBean
    private ConferenceService conferenceService;

    private static final String CONTENT_TYPE = "application/json";

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        //mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void givenParticipantId_whenMakingGetRequestToParticipantEndpoint_thenReturnParticipant() throws Exception {

        UUID id = UUID.randomUUID();
        Participant testParticipant = new Participant(id, "firstName", "lastName", "title", "email",
                "phoneNumber", "username", "password", null, false, false, null);

        System.out.println(testParticipant.getIsOrganiser() + " " + testParticipant.getIsSpeaker());

        when(participantService.findById(id)).thenReturn(Optional.of(testParticipant));

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/participants/" + testParticipant.getId(), id.toString()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(testParticipant.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(testParticipant.getLastName()))
                .andExpect(jsonPath("$.title").value(testParticipant.getTitle()))
                .andExpect(jsonPath("$.email").value(testParticipant.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(testParticipant.getPhoneNumber()))
                .andExpect(jsonPath("$.username").value(testParticipant.getUsername()))
                .andExpect(jsonPath("$.organiser").value(testParticipant.getIsOrganiser()))
                .andExpect(jsonPath("$.speaker").value(testParticipant.getIsSpeaker()))
                .andReturn();
    }

    @Test
    public void givenConferenceId_whenMakingGetRequestToParticipantsEndpoint_thenReturnParticipant() throws Exception {

        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();


        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());

        Participant testParticipant1 = new Participant(id1, "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, true, true, testConference);

        Participant testParticipant2 = new Participant(id2, "firstName2", "lastName2", "title2", "email2",
                "phoneNumber2", "username2", "password2", null, false, false, testConference);

        List<Participant> participants = new ArrayList<>();
        participants.add(testParticipant1);
        participants.add(testParticipant2);

        when(conferenceService.findById(id)).thenReturn(Optional.of(testConference));
        when(participantService.findByConference_Id(id)).thenReturn(participants);

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/conferences/" + testConference.getId() + "/participants", id.toString()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenMakingPostRequestToParticipantsEndpoint_thenReturnResponse() throws Exception {

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

        String json = new ObjectMapper().writeValueAsString(participantCreationDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/conferences/" + testConference.getId() + "/participants", id.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(participantCreationDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(participantCreationDTO.getLastName()))
                .andExpect(jsonPath("$.title").value(participantCreationDTO.getTitle()))
                .andExpect(jsonPath("$.email").value(participantCreationDTO.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(participantCreationDTO.getPhoneNumber()))
                //.andExpect(jsonPath("$.isOrganiser").value(participantCreationDTO.getOrganiser()))
                //.andExpect(jsonPath("$.isSpeaker").value(participantCreationDTO.getSpeaker()))
                .andReturn();
    }

    @Test
    public void whenMakingPutRequestToParticipantsEndpoint_thenReturnResponse() throws Exception {

        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant1 = new Participant(id1, "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, true, true, testConference);
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

        String json = new ObjectMapper().writeValueAsString(participantCreationDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/participants/" + id1, id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(participantCreationDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(participantCreationDTO.getLastName()))
                .andExpect(jsonPath("$.title").value(participantCreationDTO.getTitle()))
                .andExpect(jsonPath("$.email").value(participantCreationDTO.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(participantCreationDTO.getPhoneNumber()))
                //.andExpect(jsonPath("$.isOrganiser").value(participantCreationDTO.getOrganiser()))
                //.andExpect(jsonPath("$.isSpeaker").value(participantCreationDTO.getSpeaker()))
                .andReturn();
    }

    @Test
    public void whenMakingPatchRequestToConferenceEndpoint_thenReturnResponse() throws Exception {

        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());

        Participant testParticipant1 = new Participant(id1, "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, true, true, testConference);

        when(participantService.findById(id1)).thenReturn(Optional.of(testParticipant1));


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

        String json = new ObjectMapper().writeValueAsString(participantCreationDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/participants/" + id1, id.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(participantCreationDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(participantCreationDTO.getLastName()))
                .andExpect(jsonPath("$.title").value(participantCreationDTO.getTitle()))
                .andExpect(jsonPath("$.email").value(participantCreationDTO.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(participantCreationDTO.getPhoneNumber()))
                //.andExpect(jsonPath("$.isOrganiser").value(participantCreationDTO.getOrganiser()))
                //.andExpect(jsonPath("$.isSpeaker").value(participantCreationDTO.getSpeaker()))
                .andReturn();
    }

    @Test
    public void whenMakingDeleteRequestToParticipantsEndpoint_thenReturnResponse() throws Exception {

        //TO DO: adjust test to prepopulate table entry to be deleted (mock)
        // mockito verify

        UUID id = UUID.randomUUID();
        UUID id1 = UUID.randomUUID();

        Conference testConference = new Conference(id, new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>());
        Participant testParticipant1 = new Participant(id1, "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, true, true, testConference);
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

        String json = new ObjectMapper().writeValueAsString(participantCreationDTO);

        mockMvc.perform(put(uri + "/participants/" + id1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(delete(uri + "/participants/" + id1))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
