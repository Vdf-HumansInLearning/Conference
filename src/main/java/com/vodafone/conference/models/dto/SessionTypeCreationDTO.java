package com.vodafone.conference.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionTypeCreationDTO {

    private String type;
    private int sessionLength;

}
