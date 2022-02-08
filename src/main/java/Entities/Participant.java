package Entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participant {

    @Id
    @Column(name = "participant_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private Integer participant_id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(mappedBy = "participant")
    private Speaker speaker;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session sessions;

    @OneToMany(mappedBy = "participant")
    private List<Ticket> tickets;

    @Column(name = "is_organiser", columnDefinition = "tinyint(1) default 0")
    private Boolean is_organiser;

    @Column(name = "is_speaker", columnDefinition = "tinyint(1) default 0")
    private Boolean is_speaker;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;

}