package com.vodafone.conference.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackCreationDTO {
    private String title;
    private Day day;
    private List<Session> sessions;
    private Conference conference;
}
