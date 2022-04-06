package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "session")
public class Session extends EntityWithUUID {
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany @JsonIgnore
    private List<Speaker> speakers;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(cascade = CascadeType.ALL) @JsonIgnore
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

    @OneToMany(mappedBy = "sessions") @JsonIgnore
    private List<Participant> participants;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    enum TechLevel {
        BEGINNER,
        MID_LEVEL,
        ADVANCED;
    }
}
