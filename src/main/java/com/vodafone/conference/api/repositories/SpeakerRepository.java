package com.vodafone.conference.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vodafone.conference.models.entities.Speaker;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpeakerRepository extends JpaRepository<Speaker, UUID>{

    // JPA Palette generated
    //Optional<Participant> findByIdEquals(UUID id);
    @Override
    List<Speaker> findAll();

    @Override
    Optional<Speaker> findById(UUID uuid);

    @Override
    void deleteById(UUID uuid);


}
