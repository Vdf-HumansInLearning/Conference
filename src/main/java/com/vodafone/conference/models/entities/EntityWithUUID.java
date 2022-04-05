package com.vodafone.conference.models.entities;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Data
public class EntityWithUUID implements Serializable {
    @Id @Type(type = "pg-uuid")
    private UUID id;

    public EntityWithUUID() {
        this.id = UUID.randomUUID();
    }
}
