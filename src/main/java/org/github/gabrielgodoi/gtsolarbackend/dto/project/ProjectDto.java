package org.github.gabrielgodoi.gtsolarbackend.dto.project;

import org.github.gabrielgodoi.gtsolarbackend.dto.client.ClientDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.equipments.EquipmentsDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.person.EngineerDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.person.InstallerDto;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;

import java.util.List;

public record ProjectDto(
        String id,
        String name,
        StatusEnum status,
        ClientDto client,
        EngineerDto engineer,
        InstallerDto installer,
        String energyConsumption,
        List<EquipmentsDto> equipmentsDto,
        Double approvedValue
) {
}
