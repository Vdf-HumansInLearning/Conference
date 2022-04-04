package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.entities.DTO.SessionCreationDTO;
import com.vodafone.conference.models.entities.DTO.SessionDTO;
import com.vodafone.conference.models.entities.DTO.SessionsTypeCreationDTO;
import com.vodafone.conference.models.entities.DTO.SessionsTypeDTO;
import com.vodafone.conference.models.entities.Session;
import com.vodafone.conference.models.entities.SessionType;
import org.modelmapper.ModelMapper;

public class SessionsTypeMapper {

    ModelMapper mapper = new ModelMapper();

    public SessionsTypeDTO toDto(SessionType sessionType) {

        SessionsTypeDTO sessionsTypeDTO = mapper.map(sessionType, SessionsTypeDTO.class);

        return sessionsTypeDTO;
    }

    public SessionType toSessionType(SessionsTypeCreationDTO sessionsTypeCreationDTO) {

        SessionType sessionType = mapper.map(sessionsTypeCreationDTO, SessionType.class);
        return sessionType;
    }
}
