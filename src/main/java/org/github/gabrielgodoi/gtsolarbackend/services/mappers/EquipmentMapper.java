package org.github.gabrielgodoi.gtsolarbackend.services.mappers;

import org.github.gabrielgodoi.gtsolarbackend.dto.equipments.EquipmentsDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Equipment;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EquipmentMapper {
    Equipment toEntity(EquipmentsDto entity);
    EquipmentsDto toDto(Equipment dto);
}
