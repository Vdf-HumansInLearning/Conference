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

    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
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

    @ManyToMany
    private List<Session> sessions;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;
}