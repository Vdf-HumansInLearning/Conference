package com.vodafone.conference.models.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SessionCreationDTO {

    private String title;
    private String description;
    private SessionTypeCreationDTO sessionTypeCreationDTO;
    private String topic;
    private String techLevel;
    private List<String> keywords;

    private LocalDate date;
    private LocalDate endTime;

}
