package com.vodafone.conference.models.entities.DTO;

import java.util.UUID;

public class ParticipantIdDTO {

    private UUID id;

    public ParticipantIdDTO(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
