package com.vodafone.conference.services;

import com.vodafone.conference.api.repositories.DayRepository;
import com.vodafone.conference.models.dto.BaseDayDTO;
import com.vodafone.conference.models.entities.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DayService {

    @Autowired
    private DayRepository dayRepository;

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
            throw new RuntimeException(String.format("Could not find a day with the id: ", id));
        }
    }

    @Transactional
    public List<BaseDayDTO> getAllDays() {
        List<Day> days = dayRepository.findAll();
        List<BaseDayDTO> baseDayDTOList = new ArrayList<>();

        for (Day day : days) {
            BaseDayDTO temp = new BaseDayDTO();
            temp.date = day.getDate();
            temp.conference = day.getConference();
            baseDayDTOList.add(temp);
        }
        return baseDayDTOList;
    }

    @Transactional
    public void deleteById(UUID id) {
        Optional<Day> byId = dayRepository.findById(id);
        if (byId.isPresent()) {
            dayRepository.deleteById(id);
        } else {
            throw new RuntimeException(String.format("Could not find a day with the id: ", id));
        }
    }
}