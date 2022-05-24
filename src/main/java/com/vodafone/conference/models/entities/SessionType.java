package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "session_length")
    private int sessionLength;

    @OneToOne(mappedBy = "sessionType", cascade = CascadeType.ALL) @JsonIgnore
    private Session session;

}
