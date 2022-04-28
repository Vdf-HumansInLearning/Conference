package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.TicketRepository;
import com.vodafone.conference.exceptions.ApiRequestException;
import com.vodafone.conference.models.dto.TicketDTO;
import com.vodafone.conference.models.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

//    @Autowired
    private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Optional<Ticket> findById(UUID id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}
