package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "session")
public class Session extends EntityWithUUID {

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="speaker_session", joinColumns = @JoinColumn(name="session_id"),
            inverseJoinColumns = @JoinColumn(name="speaker_id"))
    private List<Speaker> speakers;

    public void addSpeaker(Speaker speaker) {
        this.speakers.add(speaker);
    }

    public void removeSpeaker(UUID speakerId) {
        Speaker speaker = this.speakers.stream().filter(sp -> sp.getId().equals(speakerId)).findFirst().get();
        this.speakers.remove(speaker);
    }

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_type_id")
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

    @OneToMany(mappedBy = "sessions", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Participant> participants;

}
