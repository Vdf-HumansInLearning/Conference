package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id")
    @JsonBackReference
    private Day day;

//    @OneToMany(mappedBy = "track") @JsonIgnore
    @OneToMany @JsonIgnore
    private List<Session> sessions;

    @ManyToOne @JsonIgnore
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;
}
