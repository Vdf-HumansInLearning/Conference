package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "session")
public class Session extends EntityWithUUID {
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany
    @JsonIgnore
    private List<Speaker> speakers;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private SessionType sessionType;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "tech_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private TechLevel techLevel;

    @Column(name = "keywords", nullable = false)
    private String keywords;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "end_time", nullable = false)
    private LocalDate endTime;

    @Column(name = "review", nullable = false)
    private int review;

    // participant should not have session ID
    // as sessions added/created by participants are unique to them (i.e. two participants cannot add/create the same session)
    //@OneToMany(mappedBy = "sessions")
    //private List<Participant> participants;

    @OneToMany(mappedBy = "sessions")
    @JsonIgnore
    private List<Participant> participants;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    public enum TechLevel {
        BEGINNER,
        MID_LEVEL,
        ADVANCED
    }

    public void addSpeaker(Speaker speaker) {
        this.speakers.add(speaker);
    }

    public void removeSpeaker(UUID speakerId) {
        Speaker speaker = this.speakers.stream().filter(prod -> prod.getId() == speakerId).findFirst().orElse(null);
        if (speaker != null) this.speakers.remove(speaker);
    }
}

