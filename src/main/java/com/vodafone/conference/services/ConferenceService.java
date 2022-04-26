package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.ConferenceRepository;
import com.vodafone.conference.models.entities.Conference;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConferenceService implements Serializable {

    private ConferenceRepository conferenceRepository;

    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public Optional<Conference> findById(UUID id) {
        return conferenceRepository.findById(id);
    }

    public void save(Conference conference) {
        Objects.requireNonNull(conference);
        conferenceRepository.save(conference);
    }

    public void update(Conference conference, UUID id) {
        Objects.requireNonNull(conference);
        //conferenceRepository.update(conference.getDays(), conference.getLocation(), conference.getTheme(), conference.getDescription(), conference.getParticipants(), id);
        conferenceRepository.update(conference.getLocation(), conference.getTheme(), conference.getDescription(), id);
    }

    public void deleteById(UUID id) {
        conferenceRepository.deleteById(id);
    }
}
