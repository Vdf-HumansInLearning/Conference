package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "day")
@IdClass(value = EntityWithUUID.class)
public class Day extends EntityWithUUID implements Serializable {
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;

    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true) @JsonIgnore
    @JsonManagedReference
    private List<Track> track;

    public Day(LocalDate date) {
        this.date = date;
    }

    public Day(UUID id, LocalDate date, Conference conference) {
        this.id = id;
        this.date = date;
        this.conference = conference;
    }

    public Day(LocalDate date, Conference conference) {
        this.date = date;
        this.conference = conference;
    }
}