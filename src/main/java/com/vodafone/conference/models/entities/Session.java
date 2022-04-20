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

//    @ManyToMany
//    @JsonIgnore
//    private List<Speaker> speakers;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private SessionType sessionType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "tech_level", nullable = false)
    private String techLevel;

    @Column(name = "keywords", nullable = false)
    private String keywords;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "end_time", nullable = false)
    private LocalDate endTime;

    @Column(name = "review")
    private int review;

    @OneToMany(mappedBy = "sessions")
    @JsonIgnore
    private List<Participant> participants;

}
