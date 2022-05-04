package com.vodafone.conference.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.vodafone.conference.ConferenceApplication;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {ConferenceApplication.class})
public class ConferenceControllerMockMVCTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String uri = "http://localhost:5432";

    @MockBean
    private ConferenceService conferenceService;

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getConferences() throws Exception {
        Conference conference = new Conference(new ArrayList<>(), "location1",
                "theme1", "description1", new ArrayList<>(), new ArrayList<>());

        Conference conference2 = new Conference(new ArrayList<>(), "location2",
                "theme2", "description2", new ArrayList<>(), new ArrayList<>());

        List<Conference> conferenceList = new ArrayList<>();
        conferenceList.add(conference);
        conferenceList.add(conference2);

        when(conferenceService.findAll()).thenReturn(conferenceList);

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/conferences").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getConferenceById() throws Exception {
        Conference conference = new Conference(new ArrayList<>(), "location1",
                "theme1", "description1", new ArrayList<>(), new ArrayList<>());

        UUID id = conference.getId();

        when(conferenceService.findById(id)).thenReturn(Optional.of(conference));

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/conferences/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value(conference.getLocation()))
                .andExpect(jsonPath("$.theme").value(conference.getTheme()))
                .andExpect(jsonPath("$.description").value(conference.getDescription()))
                .andReturn();
    }

    @Test
    void postConference() throws Exception {
        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO("Test Location", "Test Theme", "Test Description");

        String json = new ObjectMapper().writeValueAsString(conferenceCreationDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/conferences")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.location").value(conferenceCreationDTO.getLocation()))
                .andExpect(jsonPath("$.theme").value(conferenceCreationDTO.getTheme()))
                .andExpect(jsonPath("$.description").value(conferenceCreationDTO.getDescription()))
                .andReturn();
    }

    @Test
    void putConferenceById() throws Exception {
        Conference conference = new Conference(new ArrayList<>(), "location1",
                "theme1", "description1", new ArrayList<>(), new ArrayList<>());

        UUID id = conference.getId();

        ConferenceCreationDTO conferenceCreationDTO = new ConferenceCreationDTO("Put Test Location", "Put Test Theme", "Put Test Description");
        String json = new ObjectMapper().writeValueAsString(conferenceCreationDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/conferences/{id}", id).accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value(conferenceCreationDTO.getLocation()))
                .andExpect(jsonPath("$.theme").value(conferenceCreationDTO.getTheme()))
                .andExpect(jsonPath("$.description").value(conferenceCreationDTO.getDescription()))
                .andReturn();
    }

    @Test
    void deleteConference() throws Exception {
        Conference conference = new Conference(new ArrayList<>(), "location1",
                "theme1", "description1", new ArrayList<>(), new ArrayList<>());

        UUID id = conference.getId();

        when(conferenceService.findById(id)).thenReturn(Optional.of(conference));

        mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/conferences/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
