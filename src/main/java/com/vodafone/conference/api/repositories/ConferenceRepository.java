package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConferenceRepository extends JpaRepository<Conference, UUID> {
    Optional<Conference> findById(UUID id);

    @Transactional
    @Modifying
    @Query("update Conference c set c.description = ?1, c.theme = ?2, c.location = ?3 where c.id = ?4")
    void update(String description, String theme, String location, UUID id);
}
