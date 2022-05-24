package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.dto.SessionTypeCreationDTO;
import com.vodafone.conference.models.dto.SessionTypeDTO;
import com.vodafone.conference.models.entities.SessionType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SessionTypeMapper {

    ModelMapper mapper = new ModelMapper();

    public SessionTypeDTO toDto(SessionType sessionType) {
        return mapper.map(sessionType, SessionTypeDTO.class);
    }

    public SessionType toSessionType(SessionTypeCreationDTO sessionTypeCreationDTO) {
        return mapper.map(sessionTypeCreationDTO, SessionType.class);
    }

}
