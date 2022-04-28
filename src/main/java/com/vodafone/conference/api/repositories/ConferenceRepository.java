package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ConferenceRepository extends JpaRepository<Conference, UUID> {
    Optional<Conference> findById(UUID id);
}
