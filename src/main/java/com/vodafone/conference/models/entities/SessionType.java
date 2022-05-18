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

    @Column(name = "type")
    private String type;

    @Column(name = "length")
    private int sessionLength;

    @OneToOne(mappedBy = "sessionType", cascade = CascadeType.ALL)
    private Session session;

}
