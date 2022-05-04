package com.vodafone.conference.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.vodafone.conference.ConferenceApplication;
import com.vodafone.conference.models.dto.SessionTypeCreationDTO;
import com.vodafone.conference.models.entities.Session;
import com.vodafone.conference.models.entities.SessionType;
import com.vodafone.conference.services.SessionTypeService;
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
class SessionTypeControllerMockMVCTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String uri = "http://localhost:5433";

    @MockBean
    private SessionTypeService sessionTypeService;

    private static final String CONTENT_TYPE = "application/json";

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void createSessionType() throws Exception {
        SessionTypeCreationDTO sessionTypeCreationDto = new SessionTypeCreationDTO("Test ", 999);

        String json = new ObjectMapper().writeValueAsString(sessionTypeCreationDto);

        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/session_types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value(sessionTypeCreationDto.getType()))
                .andExpect(jsonPath("$.sessionLength").value(sessionTypeCreationDto.getSessionLength()))
                .andReturn();
    }

    @Test
    void getSessionTypes() throws Exception {
        Session session1 = new Session();
        SessionType sessionType1 = new SessionType("Test", 100, session1);

        Session session2 = new Session();
        SessionType sessionType2 = new SessionType("Test", 200, session2);

        List<SessionType> sessionTypeList = new ArrayList<>();
        sessionTypeList.add(sessionType1);
        sessionTypeList.add(sessionType2);

        when(sessionTypeService.findAll()).thenReturn(sessionTypeList);

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/session_types").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getSessionTypeById() throws Exception {
        Session session = new Session();

        SessionType sessionType = new SessionType("Test", 999, session);
        UUID sessionTypeId = sessionType.getId();

        when(sessionTypeService.findById(sessionTypeId)).thenReturn(Optional.of(sessionType));

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/session_types/{sessoionTypeId}", sessionTypeId.toString()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(sessionType.getType()))
                .andExpect(jsonPath("$.sessionLength").value(sessionType.getSessionLength()))
                .andReturn();
    }

    @Test
    void putSessionTypeById() throws Exception {
        Session session = new Session();

        SessionType sessionType = new SessionType("Test", 100, session);
        UUID sessionTypeId = sessionType.getId();

        SessionTypeCreationDTO sessionTypeCreationDto = new SessionTypeCreationDTO("PutTest ", 200);
        String json = new ObjectMapper().writeValueAsString(sessionTypeCreationDto);

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/session_types/{sessoionTypeId}", sessionTypeId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(sessionTypeCreationDto.getType()))
                .andExpect(jsonPath("$.sessionLength").value(sessionTypeCreationDto.getSessionLength()))
                .andReturn();
    }

    @Test
    void deleteSessionType() throws Exception {
        Session session = new Session();

        SessionType sessionType = new SessionType("Test", 999, session);
        UUID sessionTypeId = sessionType.getId();

        when(sessionTypeService.findById(sessionTypeId)).thenReturn(Optional.of(sessionType));

        mockMvc.perform(MockMvcRequestBuilders.delete(uri + "/session_types/{sessoionTypeId}", sessionTypeId.toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

}
