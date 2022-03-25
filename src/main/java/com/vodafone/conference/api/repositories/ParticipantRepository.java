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

    List<Participant> findBySessions_Id(UUID id);

    List<Participant> findByConference_Id(UUID id);

    @Override
    void deleteById(UUID uuid);

}
