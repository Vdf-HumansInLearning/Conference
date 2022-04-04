package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
}