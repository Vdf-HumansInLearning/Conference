package com.vodafone.conference.api.repositories;

import org.springframework.data.repository.CrudRepository;
import com.vodafone.conference.models.entities.Speaker;

import java.util.Optional;
import java.util.UUID;

public interface SpeakerRepository extends CrudRepository<Speaker, UUID>{

    // JPA Palette generated
    //Optional<Participant> findByIdEquals(UUID id);
    @Override
    Optional<Speaker> findById(UUID uuid);

    // TO DO: implement save method to POST/insert speakers

    @Override
    void deleteById(UUID uuid);


}
