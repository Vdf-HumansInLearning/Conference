package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.dto.DayCreationDTO;
import com.vodafone.conference.models.dto.DayDTO;
import com.vodafone.conference.models.entities.Day;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class DayMapper {
    ModelMapper mapper = new ModelMapper();

    public DayDTO toDto(Day day) {
        return mapper.map(day, DayDTO.class);
    }

    public Day toDay(DayCreationDTO dayCreationDTO) {
        return mapper.map(dayCreationDTO, Day.class);
    }

    public Day toDay(DayDTO dayDTO) {
        return mapper.map(dayDTO, Day.class);
    }
}
