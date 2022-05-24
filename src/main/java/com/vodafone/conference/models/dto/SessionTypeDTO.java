package com.vodafone.conference.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionTypeDTO {

    private UUID id;
    private String type;
    private int sessionLength;

}
