package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class Ticket extends EntityWithUUID {
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Column(name = "price", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType price;

    @ManyToOne @JsonIgnore
    @JoinColumn(name="participant_id", nullable=false)
    private Participant participant;

    @ManyToOne @JsonIgnore
    @JoinColumn(name="ticket_id", nullable=false)
    private Conference conference;


    public enum TicketType {
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
