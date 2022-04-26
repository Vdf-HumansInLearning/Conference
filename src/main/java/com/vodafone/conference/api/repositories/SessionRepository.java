package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {

    @Transactional
    @Modifying

    @Query("update Session s set s.title = ?1, s.description = ?2, s.topic = ?3, s.techLevel = ?4, s.keywords = ?5," +
            " s.date = ?6, s.endTime = ?7 where s.id = ?8")
    void update(String title, String description, String topic, String techLevel, String keywords,
                LocalDate date, LocalDate endTime, UUID id);
}
