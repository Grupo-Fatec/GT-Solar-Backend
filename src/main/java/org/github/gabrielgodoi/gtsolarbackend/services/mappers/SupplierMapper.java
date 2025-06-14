package org.github.gabrielgodoi.gtsolarbackend.services.mappers;

import org.github.gabrielgodoi.gtsolarbackend.dto.supplier.*;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Supplier;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface SupplierMapper {
    SupplierDto toDto(Supplier entity);
    Supplier toEntity(InsertSupplierDto dto);
}
