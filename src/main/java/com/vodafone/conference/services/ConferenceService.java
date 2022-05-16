package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.ConferenceRepository;
import com.vodafone.conference.models.entities.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }


    @Transactional
    public Boolean isIdPresent(UUID id) {
        return conferenceRepository.findById(id).isPresent();
    }

    @Transactional
    public List<Conference> findAll() {
        return conferenceRepository.findAll();
    }

    public Optional<Conference> findById(UUID id) {
        return conferenceRepository.findById(id);
    }

    @Transactional
    public void save(Conference conference){
        Objects.requireNonNull(conference);
        conferenceRepository.save(conference);
    }

    public void update(Conference conference, UUID id) {
        Objects.requireNonNull(conference);

        conferenceRepository.update(conference.getDescription(), conference.getTheme(), conference.getLocation(), id);
    }

    public void deleteById(UUID id) {
         conferenceRepository.deleteById(id);
    }
}
