package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.ParticipantRepository;
import com.vodafone.conference.api.repositories.SpeakerRepository;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Speaker;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class SpeakerService {

    private SpeakerRepository speakerRepository;

    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    public Optional<Speaker> findById(UUID id) {
        return speakerRepository.findById(id);
    }

    public void save(Speaker speaker) {
        Objects.requireNonNull(speaker);
        speakerRepository.save(speaker);
    }
}
