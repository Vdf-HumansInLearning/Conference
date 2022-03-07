package com.vodafone.conference.api.repositories;

import org.springframework.data.repository.CrudRepository;
import com.vodafone.conference.models.entities.Participant;

import java.util.Optional;
import java.util.UUID;

public interface ParticipantRepository extends CrudRepository<Participant, UUID> {

    // JPA Palette generated
    //Optional<Participant> findByIdEquals(UUID id);
    @Override
    Optional<Participant> findById(UUID uuid);

    // TO DO: implement save method to POST/insert participants

    @Override
    void deleteById(UUID uuid);




    // Implemented
    //Optional<Participant> findById(UUID id);
    //Participant save(Participant participant);
    //void deleteById(UUID uuid)

}
