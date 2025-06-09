package org.github.gabrielgodoi.gtsolarbackend.dto.supplier;

import org.github.gabrielgodoi.gtsolarbackend.dto.equipments.EquipmentsDto;

import java.time.LocalDateTime;
import java.util.List;

public record SupplierDto(
        String id,
        String name,
        String email,
        LocalDateTime deliveryDate,
        List<EquipmentsDto> equipmentsDtos
) {
}
