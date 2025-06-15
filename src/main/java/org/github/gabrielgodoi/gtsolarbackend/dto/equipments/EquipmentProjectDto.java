package org.github.gabrielgodoi.gtsolarbackend.dto.equipments;

import jakarta.validation.constraints.*;
import org.github.gabrielgodoi.gtsolarbackend.enums.EquipmentType;

public record EquipmentProjectDto(
        @NotNull
        String id,
        @Min(3)
        String name,
        EquipmentType type,
        @NotBlank
        String power,
        @NotNull
        @PositiveOrZero(message = "O preço não pode ser negativo")
        Double price,
        String guarantee,
        String supplierId,
        @Positive(message = "A quantidade só pode ser com núemeros acima de zero")
        Integer quantity
) {
}
