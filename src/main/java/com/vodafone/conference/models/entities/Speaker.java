package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Data
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

    // this is Spring many-to-many relationship in course
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name="speaker_session", joinColumns = @JoinColumn(name="speaker_id"),
                inverseJoinColumns = @JoinColumn(name="session_id"))
    private Set<Session> sessions = new HashSet<>();

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public void removeSession(UUID sessionId) {
        Session session = this.sessions.stream().filter(ses -> ses.getId() == sessionId).findFirst().orElse(null);
        if (session != null) this.sessions.remove(session);
    }

    // speaker should not have session ID
    // as sessions added/created by speakers are unique to them (i.e. two speakers cannot add/create the same session)
    //@ManyToOne
    //@JoinColumn(name = "session_id", nullable = false)
    //private Session sessions;
    @ManyToMany @JsonIgnore
    private List<Session> sessions;

    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false)
    private Conference conference;
}
