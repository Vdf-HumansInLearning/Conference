package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ConferenceRepository extends JpaRepository<Conference, UUID> {

    @Override
    Optional<Conference> findById(UUID uuid);

    @Override
    void deleteById(UUID uuid);

    //@Query("INSERT INTO CONFERENCE VALUES(:id)")
    //void createConference(@Param(""))
}
