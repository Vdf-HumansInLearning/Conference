package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.SessionRepository;
import com.vodafone.conference.models.entities.Session;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void save(Session session) {
        Objects.requireNonNull(session);
        sessionRepository.save(session);
    }

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Optional<Session> findById(UUID id) {
        return sessionRepository.findById(id);
    }

    public void update(Session session, UUID id) {
        Objects.requireNonNull(session);
        sessionRepository.update(id);
    }

    public void deleteById(UUID id) {
        sessionRepository.deleteById(id);
    }

}
