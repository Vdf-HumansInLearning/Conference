package com.vodafone.conference.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Conference {

    //columnDefinition = "uuid DEFAULT uuid_generate_v4()"
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OneToMany(mappedBy = "conference")
    private List<Day> days;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "theme", nullable = false)
    private String theme;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "id")
    private List<Participant> participants;

    @Override
    public String toString() {
        return "Conference{" +
                "id=" + id +
                ", days=" + days +
                ", location='" + location + '\'' +
                ", theme='" + theme + '\'' +
                ", description='" + description + '\'' +
                ", participants=" + participants +
                '}';
    }
}