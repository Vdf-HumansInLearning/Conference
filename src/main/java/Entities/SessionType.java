package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SessionType {

    @Id
    @Column(name = "session_type_id", updatable = false, nullable = false, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private Integer session_type_id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private String type;

    @Column(name = "length", nullable = false)
    @Enumerated(EnumType.STRING)
    private int length;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id", referencedColumnName = "session_id")
    private Session session;
}
