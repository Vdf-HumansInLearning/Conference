package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Session;
import com.vodafone.conference.models.entities.SessionType;
import com.vodafone.conference.models.entities.Track;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SessionCreationDTO {

    private String title;
    private String description;
    private SessionType sessionType;
    private Track track;
    private String topic;
    private String techLevel;
    private List<String> keywords;

    private LocalDate date;
    private LocalDate endTime;

}
