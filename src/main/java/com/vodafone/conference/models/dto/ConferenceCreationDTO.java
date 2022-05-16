package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Speaker;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConferenceCreationDTO {

    private String location;
    private String theme;
    private String description;

    private List<Day> days;
    private List<Participant> participants;
    private List<Speaker> speakers;


}
