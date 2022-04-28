package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    @Override
    Optional<Ticket> findById(UUID id);
}
