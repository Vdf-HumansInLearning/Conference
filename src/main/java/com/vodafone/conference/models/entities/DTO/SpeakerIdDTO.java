package com.vodafone.conference.models.entities.DTO;

import java.util.UUID;

public class SpeakerIdDTO {

    private UUID id;

    public SpeakerIdDTO(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
