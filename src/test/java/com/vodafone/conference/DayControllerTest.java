package com.vodafone.conference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.conference.models.dto.DayCreationDTO;
import com.vodafone.conference.models.dto.DayDTO;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Ticket;
import com.vodafone.conference.services.DayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ ConferenceApplication.class })
public class DayControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String uri = "http://localhost:5433";

    @MockBean
    private DayService daysService;

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenDaysId_whenMakingGetRequestToDaysEndpoint_thenReturnDay() throws Exception {
        UUID id = UUID.randomUUID();
        //Ticket ticket = new Ticket(Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE, Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE);
        Ticket ticket = new Ticket("COMPLETE_ONLINE_EXPERIENCE", 200);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        Conference conference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        //Conference conference = new Conference("Bucharest", "theme", "description", tickets);
        Day testDay = new Day(id, LocalDate.now(), conference);

        when(daysService.findById(id)).thenReturn(testDay);

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/days/{id}", id.toString()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testDay.getId().toString()))
                .andExpect(jsonPath("$.date").value(testDay.getDate().toString()))
                .andReturn();
    }

    @Test
    public void whenMakingPostRequestToDayEndpoint_thenReturnResponse() throws Exception {

        //Ticket ticket = new Ticket(Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE, Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE);
        Ticket ticket = new Ticket("COMPLETE_ONLINE_EXPERIENCE", 200);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        //Conference conference = new Conference("Bucharest", "theme", "description", tickets);
        Conference conference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        DayDTO dayDTO = new DayDTO();
        dayDTO.setDate(LocalDate.of(2022, 05, 03));
        dayDTO.setConference(conference);

        String json = new ObjectMapper().writeValueAsString(dayDTO);

        mockMvc.perform(post(uri + "/days/add-day")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.date").value(dayDTO.getDate().toString()))
                .andExpect(jsonPath("$.conference.id").value(dayDTO.getConference().getId().toString()))
                .andReturn();
    }

    @Test
    public void whenMakingPutRequestToDayEndpoint_thenReturnResponse() throws Exception {

        UUID id = UUID.randomUUID();
        //Ticket ticket = new Ticket(Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE, Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE);
        Ticket ticket = new Ticket("COMPLETE_ONLINE_EXPERIENCE", 200);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        //Conference conference = new Conference("Bucharest", "theme", "description", tickets);
        Conference conference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Day testDay = new Day(id, LocalDate.now(), conference);

        when(daysService.findById(id)).thenReturn(testDay);

        DayCreationDTO dayCreationDTO = new DayCreationDTO();
        dayCreationDTO.setDate(LocalDate.of(2022, 05, 03));
        dayCreationDTO.setConference(conference);

        String json = new ObjectMapper().writeValueAsString(dayCreationDTO);

        mockMvc.perform(put(uri + "/days/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(dayCreationDTO.getDate().toString()))
                .andExpect(jsonPath("$.conference.id").value(dayCreationDTO.getConference().getId().toString()))
                .andReturn();
    }

    @Test
    public void whenMakingDeleteRequestToDayEndpoint_thenReturnResponse() throws Exception {

        UUID id = UUID.randomUUID();
        //Ticket ticket = new Ticket(Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE, Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE);
        Ticket ticket = new Ticket("COMPLETE_ONLINE_EXPERIENCE", 200);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        //Conference conference = new Conference("Bucharest", "theme", "description", tickets);
        Conference conference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Day testDay = new Day(id, LocalDate.now(), conference);

        when(daysService.findById(id)).thenReturn(testDay);

        DayCreationDTO dayCreationDTO = new DayCreationDTO();
        dayCreationDTO.setDate(LocalDate.of(2022, 05, 03));
        dayCreationDTO.setConference(conference);

        String json = new ObjectMapper().writeValueAsString(dayCreationDTO);

        mockMvc.perform(put(uri + "/days/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(delete(uri + "/days/" + id))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}
