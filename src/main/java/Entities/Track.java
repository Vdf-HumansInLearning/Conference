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
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private Integer track_id;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "track")
    private List<Day> days;

    @ManyToOne
    @JoinColumn(name = "session_id")
    public Session sessions;
}