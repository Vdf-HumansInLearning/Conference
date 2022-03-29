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
public class Speaker {

    // columnDefinition = "uuid DEFAULT uuid_generate_v4()"
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    // title also exists in participant (might want to delete this column)
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "linkedin_account", nullable = false)
    private String linkedinAcc;

    @Column(name = "twitter_account", nullable = false)
    private String twitterAcc;

    @Column(name = "github_account", nullable = false)
    private String githubAcc;

    @Column(name = "biography", nullable = false)
    private String biography;

    // check Spring many-to-many relationship in course
    //checked
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="speaker_session", joinColumns = @JoinColumn(name="speaker_id"),
                inverseJoinColumns = @JoinColumn(name="session_id"))
    private List<Session> sessions;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;
}