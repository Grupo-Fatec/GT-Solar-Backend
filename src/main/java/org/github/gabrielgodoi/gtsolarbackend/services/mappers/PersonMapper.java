package org.github.gabrielgodoi.gtsolarbackend.services.mappers;

import org.github.gabrielgodoi.gtsolarbackend.dto.person.EngineerDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.person.InstallerDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Engineer;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Installer;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Person;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PersonMapper {
    Engineer toEntity(EngineerDto dto);
    Installer toEntity(InstallerDto dto);

    EngineerDto toDto(Engineer entity);
    InstallerDto toDto(Installer entity);

    default Object toDto(Person person) {
        if (person instanceof Engineer engineer)
            return toDto(engineer);
        else if (person instanceof Installer installer)
            return toDto(installer);
        else
            throw new IllegalArgumentException("Tipo desconhecido de pessoa");
    }
}
