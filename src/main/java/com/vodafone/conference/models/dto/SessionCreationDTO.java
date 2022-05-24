package com.vodafone.conference.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
