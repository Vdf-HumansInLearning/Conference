package com.vodafone.conference.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Session {

    // columnDefinition = "uuid DEFAULT uuid_generate_v4()"
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(mappedBy = "sessions")
    private List<Speaker> speakers;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(mappedBy = "session")
    private SessionType sessionType;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "tech_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private TechLevel techLevel;

    @Column(name = "keywords", nullable = false)
    @ElementCollection
    private List<String> keywords;

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


    // Session may not have track id

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    enum TechLevel {
        BEGINNER,
        MID_LEVEL,
        ADVANCED;
    }

    public void addSpeaker(Speaker speaker) {
        this.speakers.add(speaker);
    }

    public void removeSpeaker(UUID speakerId) {
        Speaker speaker = this.speakers.stream().filter(prod -> prod.getId() == speakerId).findFirst().orElse(null);
        if (speaker != null) this.speakers.remove(speaker);
    }
}