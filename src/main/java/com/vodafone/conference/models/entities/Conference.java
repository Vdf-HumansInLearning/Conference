package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "conference")
public class Conference extends EntityWithUUID {
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore

    private List<Day> days;

    @Column(name = "location", nullable = false)
    @NotEmpty(message = "Conference must have a location")
    private String location;

    @Column(name = "theme", nullable = false)
    @NotEmpty(message = "Conference must have a theme")
    private String theme;

    @Column(name = "description", nullable = false)
    @NotEmpty(message = "Conference must have a description")
    private String description;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Participant> participants;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Speaker> speakers;

    //@OneToMany(mappedBy = "conference") @JsonIgnore
    //private List<Ticket> tickets;

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Track> tracks;
}

