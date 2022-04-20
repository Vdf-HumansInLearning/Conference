package com.vodafone.conference.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SessionTypeDTO {

    private UUID id;
    private String type;
    private int sessionLength;

}
