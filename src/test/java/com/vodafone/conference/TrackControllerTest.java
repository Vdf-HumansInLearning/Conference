package com.vodafone.conference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.conference.models.dto.DayCreationDTO;
import com.vodafone.conference.models.dto.DayDTO;
import com.vodafone.conference.models.dto.TrackCreationDTO;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Ticket;
import com.vodafone.conference.models.entities.Track;
import com.vodafone.conference.services.TrackService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ ConferenceApplication.class })
public class TrackControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String uri = "http://localhost:5433";

    @MockBean
    private TrackService trackService;

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenDaysId_whenMakingGetRequestToTracksEndpoint_thenReturnDay() throws Exception {
        UUID id = UUID.randomUUID();
        Ticket ticket = new Ticket(Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE, Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        Conference conference = new Conference("Bucharest", "theme", "description", tickets);
        Day day = new Day(id, LocalDate.now(), conference);

        Track testTrack = new Track("Test", day, conference);

        when(trackService.findById(id)).thenReturn(testTrack);

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/tracks/{id}", id.toString()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testTrack.getId().toString()))
                .andReturn();
    }

    @Test
    public void whenMakingPostRequestToDayEndpoint_thenReturnResponse() throws Exception {

        UUID id = UUID.randomUUID();
        Ticket ticket = new Ticket(Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE, Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        Conference conference = new Conference("Bucharest", "theme", "description", tickets);
        Day day = new Day(id, LocalDate.now(), conference);

        Track testTrack = new Track("Test", day, conference);

        String json = new ObjectMapper().writeValueAsString(testTrack);

        mockMvc.perform(post(uri + "/tracks/add-track")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(testTrack.getTitle()))
                .andReturn();
    }

    @Test
    public void whenMakingPutRequestToDayEndpoint_thenReturnResponse() throws Exception {

        UUID id = UUID.randomUUID();
        Ticket ticket = new Ticket(Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE, Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        Conference conference = new Conference("Bucharest", "theme", "description", tickets);
        Day day = new Day(id, LocalDate.now(), conference);

        Track testTrack = new Track("Test", day, conference);

        when(trackService.findById(id)).thenReturn(testTrack);

        TrackCreationDTO trackCreationDTO = new TrackCreationDTO();
        trackCreationDTO.setTitle("Test");
        trackCreationDTO.setDay(day);
        trackCreationDTO.setConference(conference);

        String json = new ObjectMapper().findAndRegisterModules().writeValueAsString(trackCreationDTO);

        mockMvc.perform(put(uri + "/tracks/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(testTrack.getTitle()))
                .andReturn();
    }

    @Test
    public void whenMakingDeleteRequestToDayEndpoint_thenReturnResponse() throws Exception {

        UUID id = UUID.randomUUID();
        Ticket ticket = new Ticket(Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE, Ticket.TicketType.COMPLETE_ON_SITE_EXPERIENCE);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        Conference conference = new Conference("Bucharest", "theme", "description", tickets);
        Day day = new Day(id, LocalDate.now(), conference);

        Track testTrack = new Track("Test", day, conference);

        when(trackService.findById(id)).thenReturn(testTrack);

        TrackCreationDTO trackCreationDTO = new TrackCreationDTO();
        trackCreationDTO.setTitle("Test");
        trackCreationDTO.setDay(day);
        trackCreationDTO.setConference(conference);

        String json = new ObjectMapper().findAndRegisterModules().writeValueAsString(trackCreationDTO);

        mockMvc.perform(put(uri + "/tracks/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(testTrack.getTitle()))
                .andReturn();

        mockMvc.perform(delete(uri + "/tracks/" + id))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}
