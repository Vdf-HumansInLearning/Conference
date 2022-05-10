package com.vodafone.conference.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceDTO {
    private UUID id;
    private String location;
    private String theme;
    private String description;

    private long participantNr;
}
