package org.github.gabrielgodoi.gtsolarbackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

@Getter
@AllArgsConstructor
public enum RoofType {
    CERAMICA("cerâmica"),
    METALICO("metálico"),
    FIBROCIMENTO("fibrocimento"),
    LAJE("laje"),
    MADEIRA("madeira"),
    VIDRO("vidro"),
    PVC("pvc"),
    POLICARBONATO("policarbonato"),
    SHINGLE("shingle"),
    ESTRUTURA_METALICA("estrutura metálica");

    private final String value;

    @JsonCreator
    public static RoofType fromValue(String value) {
        for (RoofType status : RoofType.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + value);
    }
}
