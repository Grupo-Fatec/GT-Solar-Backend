package org.github.gabrielgodoi.gtsolarbackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

@Getter
@AllArgsConstructor
public enum PropertyType {
    RESIDENCIAL("residencial"),
    COMERCIAL("comercial"),
    INDUSTRIAL("industrial"),
    RURAL("rural"),
    INSTITUCIONAL("institucional"),
    GOVERNO("governo"),
    RELIGIOSO("religioso"),
    MIXTO("misto"),
    CONDOMINIO("condomínio"),
    TEMPORARIO("temporário");

    private final String value;

    @JsonCreator
    public PropertyType fromValue(String value) {
        for (PropertyType status : PropertyType.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + value);
    }

}
