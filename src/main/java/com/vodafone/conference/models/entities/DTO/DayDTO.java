package com.vodafone.conference.models.entities.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DayDTO {
    public UUID id;
    public LocalDate date;
    public Conference conference;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date")
    public LocalDate getDate() {
        return date;
    };

    public DayDTO(Day day) {
        this.id = day.getId();
        this.date = day.getDate();
        this.conference = day.getConference();
    }
}