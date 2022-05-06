package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.dto.SessionCreationDTO;
import com.vodafone.conference.models.dto.SessionDTO;

import com.vodafone.conference.models.entities.Session;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SessionMapper {

    ModelMapper mapper = new ModelMapper();

    public SessionDTO toDto(Session session) {
        return mapper.map(session, SessionDTO.class);
    }

    public Session toSession(SessionCreationDTO sessionCreationDTO) {
        return mapper.map(sessionCreationDTO, Session.class);
    }

}
