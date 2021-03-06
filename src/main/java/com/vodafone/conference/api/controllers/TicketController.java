package com.vodafone.conference.api.controllers;

import com.vodafone.conference.api.mapper.TicketMapper;
import com.vodafone.conference.models.dto.TicketCreationDTO;
import com.vodafone.conference.models.dto.TicketDTO;
import com.vodafone.conference.models.entities.Ticket;
import com.vodafone.conference.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class TicketController {

    @Autowired
    private final TicketService ticketService;

    @Autowired
    private final TicketMapper ticketMapper;

    public TicketController(TicketService ticketService, TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TicketDTO>> getTickets() {
        List<TicketDTO> ticketDTOList = ticketService.findAll().stream().map(ticketMapper::toDto).collect(Collectors.toList());

        return new ResponseEntity<>(ticketDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/tickets/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable("id") UUID id) {
        Optional<Ticket> ticket = ticketService.findById(id);

        return ticket.map(tempTicket -> new ResponseEntity<>(ticketMapper.toDto(tempTicket), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/tickets", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketCreationDTO ticketCreationDTO, Errors errors) {
        Ticket ticket = ticketMapper.toTicket(ticketCreationDTO);
        TicketDTO ticketDTO = ticketMapper.toDto(ticket);

        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ticketService.save(ticket);
        return new ResponseEntity<>(ticketDTO, HttpStatus.CREATED);
    }
}
