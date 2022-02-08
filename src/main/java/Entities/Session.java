package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Session {

    @Id
    @Column(name = "session_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID session_id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany
    private List<Speaker> speakers;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToOne(mappedBy = "session")
    private SessionType sessionType;

    @Column(name = "topic", nullable = false)
    private String topic;

    @Column(name = "tech_lvl", nullable = false)
    @Enumerated(EnumType.STRING)
    private String techlvl;

    @Column(name = "keywords", nullable = false)
    @ElementCollection
    private List<String> keywords;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "end_time", nullable = false)
    private Date end_time;

    @Column(name = "review", nullable = false)
    private int review;

    @OneToMany(mappedBy = "sessions")
    private ArrayList<Participant> participants;

    @OneToMany(mappedBy = "session")
    private ArrayList<Track> tracks;
}