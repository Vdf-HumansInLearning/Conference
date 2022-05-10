package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Speaker;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class SessionDTO {

    private UUID id;
    private String title;
    private List<Speaker> speakers;
    private String description;
    private SessionTypeDTO sessionTypeDTO;
    private String topic;
    private String techLevel;
    private List<String> keywords;
    private LocalDate date;
    private LocalDate endTime;
    private int review;
    private List<Participant> participants;

}
