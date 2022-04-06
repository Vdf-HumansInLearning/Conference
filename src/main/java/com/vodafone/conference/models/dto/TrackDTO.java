package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Session;
import com.vodafone.conference.models.entities.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDTO {
    public UUID id;
    public String title;
    public Day day;
    public List<Session> sessions;
    public Conference conference;

    public TrackDTO(Track track) {
        this.id = track.getId();
        this.title = track.getTitle();
        this.day = track.getDay();
        this.sessions = track.getSessions();
        this.conference = track.getConference();
    }
}