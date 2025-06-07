package org.github.gabrielgodoi.gtsolarbackend.dto.project;

import org.github.gabrielgodoi.gtsolarbackend.dto.equipments.EquipmentsDto;

import java.util.List;

public record InsertProjectDto(
        String name,
        String clientId,
        String energyConsumption,
        String engineerId,
        String installerId,
        List<EquipmentsDto> equipmentsDto
) {

}