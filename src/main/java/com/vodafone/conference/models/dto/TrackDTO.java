package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Session;
import com.vodafone.conference.models.entities.Track;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackDTO {
    public UUID id;
    public String title;
    public LocalDate day;
    public List<Session> sessions;

    public TrackDTO(Track track) {
        this.id = track.getId();
        this.title = track.getTitle();
        this.day = track.getDay().getDate();
        this.sessions = track.getSessions();
    }
}