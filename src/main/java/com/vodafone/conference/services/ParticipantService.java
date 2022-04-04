package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.ConferenceRepository;
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
    private ConferenceRepository conferenceRepository;

    public ParticipantService(ParticipantRepository participantRepository, ConferenceRepository conferenceRepository) {
        this.participantRepository = participantRepository;
        this.conferenceRepository = conferenceRepository;
    }

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    public Optional<Participant> findById(UUID id) {
        return participantRepository.findById(id);
    }

    //public List<Participant> findBySessions_Id(UUID id) {
    //    return participantRepository.findBySessions_Id(id);
    //}

    public List<Participant> findByConference_Id(UUID id) {
        return participantRepository.findByConference_Id(id);
    }

    public void save(Participant participant, UUID conferenceId) {
        Objects.requireNonNull(participant);
          //set speaker
          participant.setSpeaker(null);
          //set tickets
          participant.setConference(conferenceRepository.findById(conferenceId).get());
          //set tracks
        participantRepository.save(participant);
    }

    public void deleteById(UUID id) {
        participantRepository.deleteById(id);
    }

    public void update(Participant participant, UUID id) {
        Objects.requireNonNull(participant);

        participantRepository.update(participant.getFirstName(), participant.getLastName(), participant.getTitle(),
                participant.getEmail(), participant.getPhoneNumber(), participant.getUsername(), participant.getPassword(), id);
    }
}
