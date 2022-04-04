package com.vodafone.conference.api.mapper;

import com.vodafone.conference.models.entities.Conference;
import com.vodafone.conference.models.entities.DTO.ConferenceCreationDTO;
import com.vodafone.conference.models.entities.DTO.ConferenceDTO;
import com.vodafone.conference.models.entities.DTO.SessionCreationDTO;
import com.vodafone.conference.models.entities.DTO.SessionDTO;
import com.vodafone.conference.models.entities.Session;
import org.modelmapper.ModelMapper;

public class SessionMapper {

    ModelMapper mapper = new ModelMapper();

    public SessionDTO toDto(Session session) {

        SessionDTO sessionDTO = mapper.map(session, SessionDTO.class);

        return sessionDTO;
    }

    public Session toSession(SessionCreationDTO sessionCreationDTO) {

        Session session = mapper.map(sessionCreationDTO, Session.class);
        return session;
    }
}
