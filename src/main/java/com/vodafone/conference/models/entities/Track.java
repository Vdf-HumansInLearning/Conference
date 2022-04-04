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
public class Track extends EntityWithUUID implements Serializable {
    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "day_id", nullable = false)
    @JsonBackReference
    private Day day;

    @OneToMany(mappedBy = "track") @JsonIgnore
    private List<Session> sessions;
}
