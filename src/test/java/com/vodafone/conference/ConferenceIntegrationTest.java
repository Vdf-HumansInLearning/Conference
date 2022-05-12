package com.vodafone.conference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.conference.models.dto.ConferenceCreationDTO;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.services.ConferenceService;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

//@RunWith(SpringRunner.class)
//@WebMvcTest()
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ ConferenceApplication.class })
public class ConferenceIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String uri = "http://localhost:5433";

    @MockBean
    private ConferenceService conferenceService;

    private static final String CONTENT_TYPE = "application/json";

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        //mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    public void givenConferenceId_whenMakingGetRequestToConferenceEndpoint_thenReturnConference() throws Exception {

        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(conferenceService.findById(testConference.getId())).thenReturn(Optional.of(testConference));

        //mock conference service
        // check for exception with Opt.isPresent()
        //when(conferenceService.findById(id)).thenReturn(Optional.of(testConference));

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/conferences/{conferenceId}", testConference.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.days").value(testConference.getDays()))
                .andExpect(jsonPath("$.location").value(testConference.getLocation()))
                .andExpect(jsonPath("$.theme").value(testConference.getTheme()))
                .andExpect(jsonPath("$.description").value(testConference.getDescription()))
                .andExpect(jsonPath("$.participants").value(testConference.getParticipants()))
                .andExpect(jsonPath("$.speakers").value(testConference.getSpeakers()))
                .andReturn();
    }

    @Test
    public void whenMakingPostRequestToConferenceEndpoint_thenReturnResponse() throws Exception {

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO();
        conferenceCreationDTO.setLocation("location");
        conferenceCreationDTO.setDescription("description");
        conferenceCreationDTO.setTheme("theme");
        //conferenceCreationDTO.setDays(new ArrayList<>());
        //conferenceCreationDTO.setParticipants(new ArrayList<>());
        //conferenceCreationDTO.setSpeakers(new ArrayList<>());

        String json = new ObjectMapper().writeValueAsString(conferenceCreationDTO);

        mockMvc.perform(post(uri + "/conferences")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.days").value(new ArrayList<>()))
                .andExpect(jsonPath("$.location").value(conferenceCreationDTO.getLocation()))
                .andExpect(jsonPath("$.theme").value(conferenceCreationDTO.getTheme()))
                .andExpect(jsonPath("$.description").value(conferenceCreationDTO.getDescription()))
                .andExpect(jsonPath("$.participants").value(new ArrayList<>()))
                .andExpect(jsonPath("$.speakers").value(new ArrayList<>()))
                .andReturn();
    }

    @Test
    public void whenMakingPutRequestToConferenceEndpoint_thenReturnResponse() throws Exception {

        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        //when(conferenceService.findById(id)).thenReturn(Optional.of(testConference));
        when(conferenceService.findById(testConference.getId())).thenReturn(Optional.of(testConference));

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO();
        conferenceCreationDTO.setLocation("location replace");
        conferenceCreationDTO.setDescription("description replace");
        conferenceCreationDTO.setTheme("theme replace");
        //conferenceCreationDTO.setDays(new ArrayList<>());
        //conferenceCreationDTO.setParticipants(new ArrayList<>());
        //conferenceCreationDTO.setSpeakers(new ArrayList<>());

        String json = new ObjectMapper().writeValueAsString(conferenceCreationDTO);

        mockMvc.perform(put(uri + "/conferences/" + testConference.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.days").value(new ArrayList<>()))
                .andExpect(jsonPath("$.location").value(conferenceCreationDTO.getLocation()))
                .andExpect(jsonPath("$.theme").value(conferenceCreationDTO.getTheme()))
                .andExpect(jsonPath("$.description").value(conferenceCreationDTO.getDescription()))
                .andExpect(jsonPath("$.participants").value(new ArrayList<>()))
                .andExpect(jsonPath("$.speakers").value(new ArrayList<>()))
                .andReturn();
    }

    @Test
    public void whenMakingPatchRequestToConferenceEndpoint_thenReturnResponse() throws Exception {

        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        //when(conferenceService.findById(id)).thenReturn(Optional.of(testConference));
        when(conferenceService.findById(testConference.getId())).thenReturn(Optional.of(testConference));

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO();
        conferenceCreationDTO.setLocation("location replace");
        conferenceCreationDTO.setDescription("description replace");
        conferenceCreationDTO.setTheme("theme replace");
        //conferenceCreationDTO.setDays(new ArrayList<>());
        //conferenceCreationDTO.setParticipants(new ArrayList<>());
        //conferenceCreationDTO.setSpeakers(new ArrayList<>());

        String json = new ObjectMapper().writeValueAsString(conferenceCreationDTO);

        mockMvc.perform(patch(uri + "/conferences/" + testConference.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.days").value(new ArrayList<>()))
                .andExpect(jsonPath("$.location").value(conferenceCreationDTO.getLocation()))
                .andExpect(jsonPath("$.theme").value(conferenceCreationDTO.getTheme()))
                .andExpect(jsonPath("$.description").value(conferenceCreationDTO.getDescription()))
                .andExpect(jsonPath("$.participants").value(new ArrayList<>()))
                .andExpect(jsonPath("$.speakers").value(new ArrayList<>()))
                .andReturn();
    }

    @Test
    public void whenMakingDeleteRequestToConferenceEndpoint_thenReturnResponse() throws Exception {

        UUID id = UUID.randomUUID();

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO();
        conferenceCreationDTO.setLocation("location replace");
        conferenceCreationDTO.setDescription("description replace");
        conferenceCreationDTO.setTheme("theme replace");
        //conferenceCreationDTO.setDays(new ArrayList<>());
        //conferenceCreationDTO.setParticipants(new ArrayList<>());
        //conferenceCreationDTO.setSpeakers(new ArrayList<>());

        String json = new ObjectMapper().writeValueAsString(conferenceCreationDTO);

        mockMvc.perform(put(uri + "/conferences/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(print());

        mockMvc.perform(delete(uri + "/conferences/" + id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
