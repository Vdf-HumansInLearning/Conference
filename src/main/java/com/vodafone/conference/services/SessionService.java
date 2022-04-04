package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.SessionRepository;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Session;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class SessionService {

    public SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Optional<Session> findById(UUID id) {
        return sessionRepository.findById(id);
    }

    public void save(Session session) {
        Objects.requireNonNull(session);
        sessionRepository.save(session);
    }

    //public void update(Session session, UUID id) {
    //    Objects.requireNonNull(session);
    //    //sessionRepository.update(, id);
    //   sessionRepository.update(, id);
    //}

    public void deleteById(UUID id) {
        sessionRepository.deleteById(id);
    }
}
