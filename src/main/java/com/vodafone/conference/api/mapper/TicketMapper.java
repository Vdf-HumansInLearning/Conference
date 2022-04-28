package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.dto.TicketCreationDTO;
import com.vodafone.conference.models.dto.TicketDTO;
import com.vodafone.conference.models.entities.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    ModelMapper ticketMapper = new ModelMapper();

    public TicketDTO toDto(Ticket ticket){
        return ticketMapper.map(ticket, TicketDTO.class);
    }

    public Ticket toTicket(TicketCreationDTO ticketCreationDTO){
        return ticketMapper.map(ticketCreationDTO, Ticket.class);
    }
}
