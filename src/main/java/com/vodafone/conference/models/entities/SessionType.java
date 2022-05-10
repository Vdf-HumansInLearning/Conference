package com.vodafone.conference.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "session_type")
public class SessionType extends EntityWithUUID {

    private String type;
    private int sessionLength;

    @OneToOne(mappedBy = "sessionType")
    @JsonIgnore
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

}
