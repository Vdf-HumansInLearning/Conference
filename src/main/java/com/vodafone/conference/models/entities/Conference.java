package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String location;

    @Column(name = "theme", nullable = false)
    private String theme;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "id")
    @JsonIgnore
    private List<Participant> participants;

//    @OneToMany(mappedBy = "conference") @JsonIgnore
//    private List<Ticket> tickets;

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Track> tracks;
}
