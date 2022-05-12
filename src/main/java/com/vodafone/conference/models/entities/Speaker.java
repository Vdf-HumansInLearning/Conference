package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

//import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity


@Table(name = "speaker")
public class Speaker extends EntityWithUUID {

    //@Transient creates error
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    // title also exists in participant (might want to delete this column)
    //@Column(name = "title", nullable = false)
    //private String title;

    @Column(name = "company", nullable = false)
    @NotBlank(message = "Company is required")
    private String company;

    @Column(name = "linkedin_acc", nullable = false)
    private String linkedinAcc;

    @Column(name = "twitter_acc", nullable = false)
    private String twitterAcc;

    @Column(name = "github_acc", nullable = false)
    private String githubAcc;

    @Column(name = "biography", nullable = false)
    @NotBlank(message = "Biography is required")
    private String biography;

    @ManyToMany(mappedBy = "speakers")
    private Set<Session> sessions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;
}
