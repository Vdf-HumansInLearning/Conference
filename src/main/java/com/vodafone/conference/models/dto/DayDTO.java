package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayDTO {
    public UUID id;
    public LocalDate date;
    public Conference conference;

    public DayDTO(Day day) {
        this.id = day.getId();
        this.date = day.getDate();
        this.conference = day.getConference();
    }
}