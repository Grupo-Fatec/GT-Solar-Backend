package org.github.gabrielgodoi.gtsolarbackend.dto.supplier;

import org.github.gabrielgodoi.gtsolarbackend.dto.equipments.EquipmentsDto;

import java.util.List;

public record SupplierDto(
        String id,
        String name,
        String email,
        String deliveryDate,
        List<EquipmentsDto> equipments
) {
}
