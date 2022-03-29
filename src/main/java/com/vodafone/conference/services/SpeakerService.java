package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.ParticipantRepository;
import com.vodafone.conference.api.repositories.SpeakerRepository;
import com.vodafone.conference.models.entities.Participant;
import com.vodafone.conference.models.entities.Speaker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
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

    public List<Speaker> findBySessions_Id(UUID id) {
        return speakerRepository.findBySessions_Id(id);
    }

    public List<Speaker> findByConference_Id(UUID id) {
        return speakerRepository.findByConference_Id(id);
    }

    public void save(Speaker speaker) {
        Objects.requireNonNull(speaker);
        speakerRepository.save(speaker);
    }

    public void deleteById(UUID id) {
        speakerRepository.deleteById(id);
    }

    public void update(Speaker speaker, UUID id) {
        Objects.requireNonNull(speaker);
        speakerRepository.update(speaker.getParticipant(), speaker.getTitle(), speaker.getCompany(), speaker.getLinkedinAcc(),
                speaker.getTwitterAcc(), speaker.getGithubAcc(), speaker.getBiography(), id);
    }
}
