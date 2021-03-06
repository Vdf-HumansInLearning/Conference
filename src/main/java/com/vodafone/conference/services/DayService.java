package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.ConferenceRepository;
import com.vodafone.conference.api.repositories.DayRepository;
import com.vodafone.conference.exceptions.ApiRequestException;
import com.vodafone.conference.models.dto.DayDTO;
import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DayService {

    @Autowired
    private DayRepository dayRepository;

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Transactional
    public Boolean isIdPresent(UUID id) {
        return dayRepository.findById(id).isPresent();
    }

    @Transactional
    public Day findById(UUID id) {
        Optional<Day> byId = dayRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }

    @Transactional
    public List<DayDTO> getAllDays() {
        List<Day> days = dayRepository.findAll();
        List<DayDTO> dayDTOList = new ArrayList<>();

        for (Day day : days) {
            DayDTO temp = new DayDTO();
            temp.id = day.getId();
            temp.date = day.getDate();
            temp.conference = day.getConference();
            dayDTOList.add(temp);
        }
        return dayDTOList;
    }

    @Transactional
    public Day findByDay(LocalDate date) {
        Optional<Day> byDate = dayRepository.findByDate(date);
        if (byDate.isPresent()) {
            return byDate.get();
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.DATE_NOT_FOUND, date.toString()));
        }
    }

    @Transactional
    public Day updateDayById(UUID id, DayDTO dayDTO) {
        Day day;
        Conference conference;

        Optional<Day> dayOptional = dayRepository.findById(id);
        Optional<Conference> conferenceOptional = conferenceRepository.findById(dayDTO.conference.getId());
        if (!dayOptional.isPresent()) {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        } else {
            day = dayOptional.get();
            conference = conferenceOptional.get();
            day.setDate(dayDTO.date);
            day.setConference(conference);
        }
        return dayRepository.save(day);
    }

    @Transactional
    public Day saveNewDay(DayDTO dayDTO) {
        Optional<Conference> conferenceOptional = conferenceRepository.findById(dayDTO.conference.getId());
        Conference conference = null;
        if (!conferenceOptional.isPresent()) {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, dayDTO.conference.getId().toString()));
        } else {
            conference = conferenceOptional.get();
        }

        Day dayToBeSaved = new Day(dayDTO.date, conference);
        Day daySaved = dayRepository.save(dayToBeSaved);
        return daySaved;
    }

    @Transactional
    public void deleteById(UUID id) {
        Optional<Day> byId = dayRepository.findById(id);
        if (byId.isPresent()) {
            dayRepository.deleteById(id);
        } else {
            throw new ApiRequestException(ApiRequestException.Exceptions.getDescription(ApiRequestException.Exceptions.ID_NOT_FOUND, id.toString()));
        }
    }
}