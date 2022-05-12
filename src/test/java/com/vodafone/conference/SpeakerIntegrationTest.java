package com.vodafone.conference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.DTO.ParticipantCreationDTO;
import com.vodafone.conference.models.entities.DTO.SpeakerCreationDTO;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Speaker;
import com.vodafone.conference.services.ConferenceService;
import com.vodafone.conference.services.ParticipantService;
import com.vodafone.conference.services.SpeakerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ ConferenceApplication.class })
public class SpeakerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private String uri = "http://localhost:5433";

    @MockBean
    private SpeakerService speakerService;

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
    public void givenSpeakerId_whenMakingGetRequestToSpeakerEndpoint_thenReturnSpeaker() throws Exception {

        //UUID id1 = UUID.randomUUID();
        //UUID id2 = UUID.randomUUID();
        //UUID id3 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Participant testParticipant = new Participant( "firstName", "lastName", "title", "email",
                "phoneNumber", "username", "password", null, null, false, false, null);

        Speaker testSpeaker = new Speaker( testParticipant, "company",
                "linkedinAcc", "twitterAcc", "githubAcc",
                "biography", new HashSet<>(), testConference);

        when(speakerService.findById(testSpeaker.getId())).thenReturn(Optional.of(testSpeaker));

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/speakers/" + testSpeaker.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company").value(testSpeaker.getCompany()))
                .andExpect(jsonPath("$.linkedinAcc").value(testSpeaker.getLinkedinAcc()))
                .andExpect(jsonPath("$.twitterAcc").value(testSpeaker.getTwitterAcc()))
                .andExpect(jsonPath("$.githubAcc").value(testSpeaker.getGithubAcc()))
                .andExpect(jsonPath("$.biography").value(testSpeaker.getBiography()))
                .andReturn();
    }

    @Test
    public void givenConferenceId_whenMakingGetRequestToSpeakersEndpoint_thenReturnSpeaker() throws Exception {

        //UUID id1 = UUID.randomUUID();
        //UUID id2 = UUID.randomUUID();
        //UUID id3 = UUID.randomUUID();
        //UUID id4 = UUID.randomUUID();
        //UUID id5 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Participant testParticipant1 = new Participant( "firstName", "lastName", "title", "email",
                "phoneNumber", "username", "password", null, null, false, false, testConference);

        Participant testParticipant2 = new Participant( "firstName2", "lastName2", "title2", "email2",
                "phoneNumber2", "username2", "password2", null, null, false, false, testConference);

        Speaker testSpeaker1 = new Speaker( testParticipant1, "company1",
                "linkedinAcc1", "twitterAcc1", "githubAcc1",
                "biography1", new HashSet<>(), testConference);

        Speaker testSpeaker2 = new Speaker( testParticipant2, "company2",
                "linkedinAcc2", "twitterAcc2", "githubAcc2",
                "biography2", new HashSet<>(), testConference);


        List<Speaker> speakers = new ArrayList<>();
        speakers.add(testSpeaker1);
        speakers.add(testSpeaker2);

        when(conferenceService.findById(testConference.getId())).thenReturn(Optional.of(testConference));
        when(speakerService.findByConference_Id(testConference.getId())).thenReturn(speakers);

        mockMvc.perform(MockMvcRequestBuilders.get(uri + "/conferences/" + testConference.getId() + "/speakers").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenMakingPostRequestToSpeakersEndpoint_thenReturnResponse() throws Exception {

        //UUID id = UUID.randomUUID();
        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(conferenceService.findById(testConference.getId())).thenReturn(Optional.of(testConference));

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


        SpeakerCreationDTO speakerCreationDTO = new SpeakerCreationDTO();
        speakerCreationDTO.setParticipantCreationDTO(participantCreationDTO);
        speakerCreationDTO.setCompany("company");
        speakerCreationDTO.setLinkedinAcc("linkedinAcc");
        speakerCreationDTO.setGithubAcc("githubAcc");
        speakerCreationDTO.setTwitterAcc("twitterAcc");
        speakerCreationDTO.setBiography("biography");

        String json = new ObjectMapper().writeValueAsString(speakerCreationDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(uri + "/conferences/" + testConference.getId() + "/speakers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.company").value(speakerCreationDTO.getCompany()))
                .andExpect(jsonPath("$.linkedinAcc").value(speakerCreationDTO.getLinkedinAcc()))
                .andExpect(jsonPath("$.twitterAcc").value(speakerCreationDTO.getTwitterAcc()))
                .andExpect(jsonPath("$.githubAcc").value(speakerCreationDTO.getGithubAcc()))
                .andExpect(jsonPath("$.biography").value(speakerCreationDTO.getBiography()))
                .andReturn();
    }

    @Test
    public void whenMakingPutRequestToSpeakersEndpoint_thenReturnResponse() throws Exception {

        //UUID id1 = UUID.randomUUID();
        //UUID id2 = UUID.randomUUID();
        //UUID id3 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Participant testParticipant1 = new Participant( "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, null, true, true, testConference);
        Speaker testSpeaker1 = new Speaker( testParticipant1, "company1",
                "linkedinAcc1", "twitterAcc1", "githubAcc1",
                "biography1", new HashSet<>(), testConference);
        when(conferenceService.findById(testConference.getId())).thenReturn(Optional.of(testConference));

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

        SpeakerCreationDTO speakerCreationDTO = new SpeakerCreationDTO();
        speakerCreationDTO.setParticipantCreationDTO(participantCreationDTO);
        speakerCreationDTO.setCompany("company");
        speakerCreationDTO.setLinkedinAcc("linkedinAcc");
        speakerCreationDTO.setGithubAcc("githubAcc");
        speakerCreationDTO.setTwitterAcc("twitterAcc");
        speakerCreationDTO.setBiography("biography");

        String json = new ObjectMapper().writeValueAsString(speakerCreationDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/speakers/" + testSpeaker1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company").value(speakerCreationDTO.getCompany()))
                .andExpect(jsonPath("$.linkedinAcc").value(speakerCreationDTO.getLinkedinAcc()))
                .andExpect(jsonPath("$.twitterAcc").value(speakerCreationDTO.getTwitterAcc()))
                .andExpect(jsonPath("$.githubAcc").value(speakerCreationDTO.getGithubAcc()))
                .andExpect(jsonPath("$.biography").value(speakerCreationDTO.getBiography()))
                .andReturn();
    }

    @Test
    public void whenMakingPatchRequestToConferenceEndpoint_thenReturnResponse() throws Exception {

        //UUID id1 = UUID.randomUUID();
        //UUID id2 = UUID.randomUUID();
        //UUID id3 = UUID.randomUUID();

        //when(speakerService.findById(id)).thenReturn(Optional.of(testSpeaker));

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Participant testParticipant1 = new Participant( "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, null, true, true, testConference);

        Speaker testSpeaker1 = new Speaker( testParticipant1, "company1",
                "linkedinAcc1", "twitterAcc1", "githubAcc1",
                "biography1", new HashSet<>(), testConference);

        when(speakerService.findById(testSpeaker1.getId())).thenReturn(Optional.of(testSpeaker1));

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

        SpeakerCreationDTO speakerCreationDTO = new SpeakerCreationDTO();
        speakerCreationDTO.setParticipantCreationDTO(participantCreationDTO);
        speakerCreationDTO.setCompany("company");
        speakerCreationDTO.setLinkedinAcc("linkedinAcc");
        speakerCreationDTO.setGithubAcc("githubAcc");
        speakerCreationDTO.setTwitterAcc("twitterAcc");
        speakerCreationDTO.setBiography("biography");

        String json = new ObjectMapper().writeValueAsString(speakerCreationDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(uri + "/speakers/" + testSpeaker1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company").value(speakerCreationDTO.getCompany()))
                .andExpect(jsonPath("$.linkedinAcc").value(speakerCreationDTO.getLinkedinAcc()))
                .andExpect(jsonPath("$.twitterAcc").value(speakerCreationDTO.getTwitterAcc()))
                .andExpect(jsonPath("$.githubAcc").value(speakerCreationDTO.getGithubAcc()))
                .andExpect(jsonPath("$.biography").value(speakerCreationDTO.getBiography()))
                .andReturn();
    }

    @Test
    public void whenMakingDeleteRequestToParticipantsEndpoint_thenReturnResponse() throws Exception {

        //UUID id1 = UUID.randomUUID();
        //UUID id2 = UUID.randomUUID();
        //UUID id3 = UUID.randomUUID();

        Conference testConference = new Conference(new ArrayList<>(), "location",
                "theme", "description", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Participant testParticipant1 = new Participant( "firstName1", "lastName1", "title1", "email1",
                "phoneNumber1", "username1", "password1", null, null, true, true, testConference);
        Speaker testSpeaker1 = new Speaker( testParticipant1, "company1",
                "linkedinAcc1", "twitterAcc1", "githubAcc1",
                "biography1", new HashSet<>(), testConference);
        when(conferenceService.findById(testConference.getId())).thenReturn(Optional.of(testConference));

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

        SpeakerCreationDTO speakerCreationDTO = new SpeakerCreationDTO();
        speakerCreationDTO.setParticipantCreationDTO(participantCreationDTO);
        speakerCreationDTO.setCompany("company");
        speakerCreationDTO.setLinkedinAcc("linkedinAcc");
        speakerCreationDTO.setGithubAcc("githubAcc");
        speakerCreationDTO.setTwitterAcc("twitterAcc");
        speakerCreationDTO.setBiography("biography");

        String json = new ObjectMapper().writeValueAsString(speakerCreationDTO);

        mockMvc.perform(put(uri + "/speakers/" + testSpeaker1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(delete(uri + "/speakers/" + testSpeaker1.getId()))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
