package com.vodafone.conference.models.utils;

import com.vodafone.conference.models.dto.DayDTO;
import com.vodafone.conference.models.entities.Day;
import org.springframework.stereotype.Service;

@Service
public class DayConverter implements EntityConverter<Day, DayDTO> {

    @Override
    public DayDTO convertToDTO(Day day) {
        DayDTO dayDTO = new DayDTO();
        dayDTO.date = day.getDate();
        dayDTO.conference = day.getConference();
        return dayDTO;
    }

    @Override
    public Day convertToEntity(DayDTO dto) {
        Day dayEntity = new Day();
        dayEntity.setDate(dto.date);
        dayEntity.setConference(dto.conference);
        return dayEntity;
    }
}