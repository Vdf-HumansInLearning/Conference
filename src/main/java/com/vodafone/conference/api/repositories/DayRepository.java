package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DayRepository extends JpaRepository<Day, UUID> {
    Optional<Day> findById(UUID id);

    @Query(value = "SELECT * FROM day  WHERE to_char(date, 'yyyy-MM-dd') LIKE %:date%", nativeQuery = true)
    Optional<Day> findByDate(@Param("date")LocalDate date);
}