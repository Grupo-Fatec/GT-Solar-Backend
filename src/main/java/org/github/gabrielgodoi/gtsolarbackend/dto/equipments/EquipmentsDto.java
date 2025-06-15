package org.github.gabrielgodoi.gtsolarbackend.dto.equipments;

import jakarta.validation.constraints.*;
import org.github.gabrielgodoi.gtsolarbackend.enums.EquipmentType;

public record EquipmentsDto(
        String id,

        @Min(3)
        String name,

        EquipmentType type,
        String power,

        @NotNull
        @PositiveOrZero(message = "O preço não pode ser negativo")
        Double price,

        String guarantee,
        String supplierId
) {
}
