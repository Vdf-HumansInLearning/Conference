package Entities;

import lombok.*;
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
    @Column(name = "speaker_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID speaker_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id")
    private Entities.Participant participant;

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
}
