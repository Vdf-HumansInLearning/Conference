package Entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // DE DEFINIT RELATIA
//    private Entities.Participant participant;

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
}