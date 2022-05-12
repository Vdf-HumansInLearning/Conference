package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "track")
@IdClass(value = EntityWithUUID.class)
public class Track extends EntityWithUUID implements Serializable {
    @Column(name = "title", nullable = false)
    private String title;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id")
    @JsonBackReference
    @JsonIgnoreProperties(value = { "day" })
    private Day day;

    @OneToMany(mappedBy = "track") @JsonIgnore
    private List<Session> sessions;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    public Track(String title, Day day, Conference conference) {
        this.title = title;
        this.day = day;
        this.conference = conference;
    }
}
