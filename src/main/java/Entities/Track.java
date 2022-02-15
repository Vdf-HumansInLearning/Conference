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
public class Track {

    @Id
    @Column(name = "track_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID track_id;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "track")
    private List<Day> days;

    @ManyToOne
    @JoinColumn(name = "session_id")
    public Session sessions;
}
