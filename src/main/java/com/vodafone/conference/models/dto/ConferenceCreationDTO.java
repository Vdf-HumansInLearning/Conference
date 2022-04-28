package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Conference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceCreationDTO {
    private String location;
    private String theme;
    private String description;

    public ConferenceCreationDTO(Conference conference){
        this.location = conference.getLocation();
        this.theme = conference.getTheme();
        this.description = conference.getDescription();
    }
}
