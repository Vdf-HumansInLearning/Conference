package com.vodafone.conference.models.entities.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SessionsTypeDTO {

    private TypeAndDuration type;
    private TypeAndDuration length;

    //private Session session;

    enum TypeAndDuration {
        WORKSHOP (45),
        DEMO (90),
        PRESENTATION (30),
        BREAK (15),
        LUNCH_BREAK (90);

        private int length;
        TypeAndDuration(int length) {
            this.length = length;
        }
    }

    public TypeAndDuration getType() {
        return type;
    }

    public void setType(TypeAndDuration type) {
        this.type = type;
    }

    public TypeAndDuration getLength() {
        return length;
    }

    public void setLength(TypeAndDuration length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "SessionsTypeDTO{" +
                "type=" + type +
                ", length=" + length +
                '}';
    }
}
