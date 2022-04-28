package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Conference;
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

    public ConferenceDTO(Conference conference){
        this.id = conference.getId();
        this.location = conference.getLocation();
        this.theme = conference.getTheme();
        this.description = conference.getDescription();
    }
}
