package org.github.gabrielgodoi.gtsolarbackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EquipmentType {
    SOLAR_PANEL("painel_solar"),
    INVERTER("inversor"),
    STRUCTURE("estrutura"),
    MONITORING_DEVICE("dispositivo_monitoramento"),
    BATTERY("bateria"),
    CONTROLLER("controlador");

    private final String value;

    @JsonCreator
    public static PaymentStatus fromValue(String value) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + value);
    }
}
