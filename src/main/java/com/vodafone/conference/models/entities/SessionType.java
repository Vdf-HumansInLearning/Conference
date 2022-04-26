package com.vodafone.conference.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SessionType {

    // columnDefinition = "uuid DEFAULT uuid_generate_v4()"
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeAndDuration type;

    @Column(name = "length", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeAndDuration length;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

    enum TypeAndDuration {
        WORKSHOP (45),
        DEMO (90),
        PRESENTATION (30),
        BREAK (15),
        LUNCH_BREAK (90);

        private int length;
        TypeAndDuration(int length) {
            this.length = length;
        }
    }
}
