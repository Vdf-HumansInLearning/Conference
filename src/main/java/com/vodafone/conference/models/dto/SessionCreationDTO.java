package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Session;
import com.vodafone.conference.models.entities.SessionType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SessionCreationDTO {

    private String title;
//    private List<Speaker> speakers;
    private String description;
    private SessionType sessionType;
    private String topic;
    private Session.TechLevel techLevel;
    private List<String> keywords;

    private LocalDate date;
    private LocalDate endTime;

//    private Track track;

}
