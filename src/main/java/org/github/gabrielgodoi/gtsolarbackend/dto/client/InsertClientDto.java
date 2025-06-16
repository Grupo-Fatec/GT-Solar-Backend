package org.github.gabrielgodoi.gtsolarbackend.dto.client;

import jakarta.validation.constraints.Email;
import org.github.gabrielgodoi.gtsolarbackend.enums.PropertyType;
import org.github.gabrielgodoi.gtsolarbackend.enums.RoofType;

public record InsertClientDto(
        String name,
        String street,
        String cep,
        String uf,
        String neighbor,
        String complement,
        @Email
        String email,
        String phone,
        String document,
        PropertyType propertyType,
        RoofType roofType
) {}
