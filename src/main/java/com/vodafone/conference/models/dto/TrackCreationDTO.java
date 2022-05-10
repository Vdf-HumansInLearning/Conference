package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackCreationDTO {

    private String title;
    private Day day;
    private Conference conference;

}
