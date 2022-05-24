package com.vodafone.conference.models.entities.DTO;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackCreationDTO {
    private String title;
    private Day day;
    private List<Session> sessions;
    private Conference conference;
}
