package com.vodafone.conference.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.conference.ConferenceApplication;
import com.vodafone.conference.models.dto.TicketCreationDTO;
import com.vodafone.conference.models.entities.Ticket;
import com.vodafone.conference.services.TicketService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {ConferenceApplication.class})
public class TicketControllerMockMVCTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String uri = "http://localhost:5432";

    @MockBean
    private TicketService ticketService;

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void getTickets() throws Exception {
        Ticket ticket1 = new Ticket("COMPLETE_ONLINE_EXPERIENCE", 200);
        Ticket ticket2 = new Ticket("COMPLETE_ON_SITE_EXPERIENCE", 350);

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        when(ticketService.findAll()).thenReturn(ticketList);

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/tickets").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getTicketById() throws Exception {
        Ticket ticket = new Ticket("COMPLETE_ONLINE_EXPERIENCE", 200);
        UUID id = ticket.getId();

        when(ticketService.findById(id)).thenReturn(Optional.of(ticket));

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/tickets/{id}", id).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(ticket.getType()))
                .andExpect(jsonPath("$.price").value(ticket.getPrice()))
                .andReturn();
    }

    @Test
    void postTickets() throws Exception {
        TicketCreationDTO ticketCreationDTO = new TicketCreationDTO("COMPLETE_ONLINE_EXPERIENCE", 200);

        String json = new ObjectMapper().writeValueAsString(ticketCreationDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value(ticketCreationDTO.getType()))
                .andExpect(jsonPath("$.price").value(ticketCreationDTO.getPrice()))
                .andReturn();
    }
}
