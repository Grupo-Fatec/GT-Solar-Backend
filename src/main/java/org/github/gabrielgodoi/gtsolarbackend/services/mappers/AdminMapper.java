package org.github.gabrielgodoi.gtsolarbackend.services.mappers;

import org.github.gabrielgodoi.gtsolarbackend.dto.admin.AdminDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.InsertAdminDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    default String map(Admin admin) {
        return admin != null ? admin.getName() : null;
    }

    Admin dtoToEntity(InsertAdminDto adminDto);

    AdminDto entityToDto(Admin admin);
}
