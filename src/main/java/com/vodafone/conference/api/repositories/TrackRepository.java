package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrackRepository extends JpaRepository<Track, UUID> {
    Optional<Track> findById(UUID id);

    //TO DO find by title ignore case
}