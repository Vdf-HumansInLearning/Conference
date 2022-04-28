package com.vodafone.conference.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreationDTO {

    private String type;
    private int price;
}
//@NoArgsConstructor
//@AllArgsConstructor

//    private Ticket.TicketType type;
//    private Ticket.TicketType price;

//    public TicketCreationDTO(Ticket ticket) {
////        this.type = ticket.getType();
////        this.price = ticket.getPrice();
//        this.type = ticket.getType();
//        this.price = ticket.getPrice();
//    }