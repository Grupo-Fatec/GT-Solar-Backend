package org.github.gabrielgodoi.gtsolarbackend.dto.project;

import org.github.gabrielgodoi.gtsolarbackend.dto.equipments.EquipmentProjectDto;

import java.util.List;

public record InsertProjectDto(
        String name,
        String clientId,
        String engineerId,
        Double energyConsumption,
        String installerId,
        List<EquipmentProjectDto> equipments
) {

}