package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.SessionTypeRepository;
import com.vodafone.conference.models.entities.SessionType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionTypeService {

    private final SessionTypeRepository sessionTypeRepository;

    public SessionTypeService(SessionTypeRepository sessionTypeRepository) {
        this.sessionTypeRepository = sessionTypeRepository;
    }

    public SessionType save(SessionType sessionType) {
        Objects.requireNonNull(sessionType);
        sessionType.setSession(null);
        sessionTypeRepository.save(sessionType);
        return sessionType;
    }

    public List<SessionType> findAll() {
        return sessionTypeRepository.findAll();
    }

    public Optional<SessionType> findById(UUID id) {
        return sessionTypeRepository.findById(id);
    }

    public void update(SessionType sessionType, UUID id) {
        Objects.requireNonNull(sessionType);
        sessionTypeRepository.update(sessionType.getType(), sessionType.getSessionLength(), id);
    }

    public void deleteById(UUID id) {
        sessionTypeRepository.deleteById(id);
    }

}
