package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.ConferenceRepository;
import com.vodafone.conference.exceptions.ApiRequestException;
import com.vodafone.conference.models.dto.ConferenceDTO;
import com.vodafone.conference.models.entities.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Transactional
    public long countConferences() {
        return conferenceRepository.findAll().stream().count();
    }

    @Transactional
    public Boolean isIdPresent(UUID id) {
        return conferenceRepository.findById(id).isPresent();
    }

    @Transactional
    public List<ConferenceDTO> getAllConferences() {
        List<Conference> conferences = conferenceRepository.findAll();
        List<ConferenceDTO> conferenceDTOList = new ArrayList<>();

        for (Conference conference : conferences) {
            ConferenceDTO temp = new ConferenceDTO();
            temp.setId(conference.getId());
            temp.setLocation(conference.getLocation());
            temp.setTheme(conference.getTheme());
            temp.setDescription(conference.getDescription());

            conferenceDTOList.add(temp);
        }

        return conferenceDTOList;
    }

    @Transactional
    public Conference findByID(UUID id) {
        Optional<Conference> conference = conferenceRepository.findById(id);

        if (conference.isPresent()) {
            return conference.get();
        }

        throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
    }

    @Transactional
    public void save(Conference conference){
        Objects.requireNonNull(conference);
        conferenceRepository.save(conference);
    }
    @Transactional
    public Conference updateConferenceById(ConferenceDTO conferenceDTO, UUID id) {
        Conference conference;
        Optional<Conference> conferenceOptional = conferenceRepository.findById(conferenceDTO.getId());

        if (conferenceOptional.isPresent()) {
            conference = conferenceOptional.get();
            conference.setLocation(conferenceDTO.getLocation());
            conference.setTheme(conferenceDTO.getTheme());
            conference.setDescription(conferenceDTO.getDescription());

            return conferenceRepository.save(conference);
        }

        throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
    }

    @Transactional
    public void deleteById(UUID id) {
         conferenceRepository.deleteById(id);
    }
}
