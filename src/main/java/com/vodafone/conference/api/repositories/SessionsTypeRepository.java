package com.vodafone.conference.api.repositories;

import com.vodafone.conference.models.entities.SessionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SessionsTypeRepository extends JpaRepository<SessionType, UUID> {
}
