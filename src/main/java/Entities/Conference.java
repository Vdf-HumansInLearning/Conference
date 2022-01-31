package Entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conference_id")
    private Integer conference_id;

    @OneToMany(mappedBy = "conference")
    private List<Day> days;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "theme", nullable = false)
    private String theme;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "schedule", nullable = false)
    private String schedule;

    @OneToMany(mappedBy = "conference")
    private List<Participant> participants;
}