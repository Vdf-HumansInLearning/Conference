package com.vodafone.conference.models.utils;

import com.vodafone.conference.models.dto.BaseDayDTO;
import com.vodafone.conference.models.dto.DayDTO;
import com.vodafone.conference.models.entities.Day;

public class DayConverter implements EntityConverter<Day, BaseDayDTO> {

    @Override
    public BaseDayDTO convertToDTO(Day day) {
        BaseDayDTO baseDayDTO = new BaseDayDTO();
        baseDayDTO.date = day.getDate();
        baseDayDTO.conference = day.getConference();
        return baseDayDTO;
    }

    @Override
    public Day convertToEntity(BaseDayDTO dto) {
        Day dayEntity = new Day();
        dayEntity.setDate(dto.date);
        dayEntity.setConference(dto.conference);
        return dayEntity;
    }
}