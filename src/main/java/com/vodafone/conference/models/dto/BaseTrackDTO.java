package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Session;
import com.vodafone.conference.models.entities.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseTrackDTO {
    public String title;
    public Day day;
    public List<Session> sessions;

    public BaseTrackDTO(Track track) {
        this.title = track.getTitle();
        this.day = track.getDay();
        this.sessions = track.getSessions();
    }
}