package com.vodafone.conference.models.dto;

import com.vodafone.conference.models.entities.Session;
import lombok.Data;

import java.util.UUID;

@Data
public class SessionTypeDTO {

    private UUID id;
    private String type;
    private int sessionLength;
    private Session session;

}
