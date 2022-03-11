package com.vodafone.conference.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vodafone.conference.models.entities.Participant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    // JPA Palette generated
    @Override
    List<Participant> findAll();

    @Override
    Optional<Participant> findById(UUID uuid);

    @Override
    void deleteById(UUID uuid);

}
