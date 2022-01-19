package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Integer session_id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "speaker_id", nullable = false)
    private Speaker speakers;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "tech_lvl", nullable = false)
    private String techlvl;

    @Column(name = "keywords", nullable = false)
    @ElementCollection
    private List<String> keywords;

    @Column(name = "length", nullable = false)
    private int length;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "review", nullable = false)
    private int review;

    @OneToMany(mappedBy="sessions")
    private ArrayList<Participant> participants;

    @OneToMany(mappedBy="session")
    private ArrayList<Track> tracks;
}
