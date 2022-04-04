package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DayRepository extends JpaRepository<Day, UUID> {

    Optional<Day> findById(UUID id);
    Optional<Day> findByDate(LocalDate date);
}