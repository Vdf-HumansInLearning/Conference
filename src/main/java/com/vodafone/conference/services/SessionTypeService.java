package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.SessionRepository;
import com.vodafone.conference.api.repositories.SessionsTypeRepository;
import com.vodafone.conference.models.entities.Session;
import com.vodafone.conference.models.entities.SessionType;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class SessionTypeService {

    public SessionsTypeRepository sessionsTypeRepository;

    public SessionTypeService(SessionsTypeRepository sessionsTypeRepository) {
        this.sessionsTypeRepository = sessionsTypeRepository;
    }

    public Optional<SessionType> findById(UUID id) {
        return sessionsTypeRepository.findById(id);
    }

    public void save(SessionType sessionType) {
        Objects.requireNonNull(sessionType);
        sessionsTypeRepository.save(sessionType);
    }

    //public void update(SessionType sessionType, UUID id) {
    //    Objects.requireNonNull(sessionType);
    //    //sessionsTypeRepository.update(, id);
    //   sessionsTypeRepository.update(, id);
    //}

    public void deleteById(UUID id) {
        sessionsTypeRepository.deleteById(id);
    }
}
