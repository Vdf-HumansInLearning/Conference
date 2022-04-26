package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Ticket {

    // columnDefinition = "uuid DEFAULT uuid_generate_v4()"
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Column(name = "price", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType price;

    /*@ManyToOne @JsonIgnore
    @JoinColumn(name="participant_id", nullable=false)
    private Participant participant;

    @ManyToOne @JsonIgnore
    @JoinColumn(name="conference_id", nullable=false)
    private Conference conference;*/

    enum TicketType {
        COMPLETE_ONLINE_EXPERIENCE (300),
        COMPLETE_ON_SITE_EXPERIENCE (350),
        ONLINE_EXPERIENCE (200),
        ON_SITE_EXPERIENCE (250);

        private int price;
        TicketType(int price) {
            this.price = price;
        }
    }
}
