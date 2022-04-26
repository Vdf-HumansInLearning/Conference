package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "speaker")
public class Speaker extends EntityWithUUID {
    @OneToOne(cascade = CascadeType.ALL) @JsonIgnore
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "linkedin_acc", nullable = false)
    private String linkedinAcc;

    @Column(name = "twitter_acc", nullable = false)
    private String twitterAcc;

    @Column(name = "github_acc", nullable = false)
    private String githubAcc;

    @Column(name = "biography", nullable = false)
    private String biography;

//    @ManyToMany(mappedBy = "speakers") @JsonIgnore
    @ManyToMany @JsonIgnore
    private List<Session> sessions;

}
