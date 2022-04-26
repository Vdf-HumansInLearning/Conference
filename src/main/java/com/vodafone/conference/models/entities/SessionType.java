package com.vodafone.conference.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "session_type")
public class SessionType extends EntityWithUUID {

    private String type;
    private int sessionLength;

    @OneToOne(mappedBy = "sessionType", cascade = CascadeType.ALL)
    private Session session;

}
