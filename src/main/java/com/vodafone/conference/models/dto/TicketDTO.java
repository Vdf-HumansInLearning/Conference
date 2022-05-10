package com.vodafone.conference.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {

    private UUID id;
    private String type;
    private int price;
}
//@NoArgsConstructor
//@AllArgsConstructor

//    private Ticket.TicketType type;
//    private Ticket.TicketType price;

//    public TicketDTO(Ticket ticket) {
//        this.id = ticket.getId();
//        this.type = ticket.getType();
//        this.price = ticket.getPrice();
//    }