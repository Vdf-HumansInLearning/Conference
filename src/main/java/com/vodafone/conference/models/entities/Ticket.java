package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket extends EntityWithUUID {

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "price", nullable = false)
    private int price;
  
    //    @ManyToOne
    //    @JsonIgnore
    //    @JoinColumn(name = "ticket_id", nullable = false)
    //    private Conference conference;

    //    @ManyToOne
    //    @JsonIgnore
    //    @JoinColumn(name="participant_id", nullable = false)
    //    private Participant participant;

    //    public enum TicketType {
    //        COMPLETE_ONLINE_EXPERIENCE (300),
    //        COMPLETE_ON_SITE_EXPERIENCE (350),
    //        ONLINE_EXPERIENCE (200),
    //        ON_SITE_EXPERIENCE (250);
    //
    //        int price;
    //        TicketType(int price) {
    //            this.price = price;
    //        }
    //    }
}
