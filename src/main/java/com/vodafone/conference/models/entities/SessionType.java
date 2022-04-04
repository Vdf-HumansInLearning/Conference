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
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "length", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Duration length;

    @OneToOne(mappedBy = "sessionType") @JsonIgnore
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

    public enum Type {
        WORKSHOP,
        DEMO,
        PRESENTATION,
        BREAK,
        LUNCH_BREAK;
    }

    public enum Duration {
        LENGTH_1 (15),
        LENGTH_2 (30),
        LENGTH_3 (45),
        LENGTH_4 (90);

        private int length;
        Duration(int length) {
            this.length = length;
        }

        public int getLength() { return length; }
    }
}
