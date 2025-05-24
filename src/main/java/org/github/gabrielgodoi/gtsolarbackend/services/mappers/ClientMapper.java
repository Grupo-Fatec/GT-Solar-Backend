package org.github.gabrielgodoi.gtsolarbackend.services.mappers;

import org.github.gabrielgodoi.gtsolarbackend.dto.client.ClientDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.client.InsertClientDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    default String map(Client client) {
        return client != null ? client.getName() : null;
    }
    Client mapToEntity(InsertClientDto insertClientDto);
    ClientDto mapToDto(Client client);
}
