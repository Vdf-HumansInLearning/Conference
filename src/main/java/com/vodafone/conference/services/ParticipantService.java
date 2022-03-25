package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.ParticipantRepository;
import com.vodafone.conference.models.entities.Participant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantService {

    private ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    public Optional<Participant> findById(UUID id) {
        return participantRepository.findById(id);
    }

    public List<Participant> findBySessions_Id(UUID id) {
        return participantRepository.findBySessions_Id(id);
    }

    public List<Participant> findByConference_Id(UUID id) {
        return participantRepository.findByConference_Id(id);
    }

    public void save(Participant participant) {
        Objects.requireNonNull(participant);
        participantRepository.save(participant);
    }

    public void deleteById(UUID id) {
        participantRepository.deleteById(id);
    }
}
