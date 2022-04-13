package com.vodafone.conference.models.entities.DTO;

import com.vodafone.conference.models.entities.Day;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Speaker;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ConferenceDTO implements Serializable {

    private List<Day> days;
    private String location;
    private String theme;
    private String description;
    private List<Participant> participants;
    private List<Speaker> speakers;

    public ConferenceDTO(List<Day> days, String location, String theme, String description, List<Participant> participants, List<Speaker> speakers) {
        this.days = days;
        this.location = location;
        this.theme = theme;
        this.description = description;
        this.participants = participants;
        this.speakers = speakers;
    }

    public ConferenceDTO() {
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public List<Speaker> getSpeakers() { return speakers; }

    public void setSpeakers(List<Speaker> speakers) { this.speakers = speakers; }

}
