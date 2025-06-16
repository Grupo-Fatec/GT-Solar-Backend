package org.github.gabrielgodoi.gtsolarbackend.dto.client;

import jakarta.validation.constraints.Email;

public record InsertClientDto(
        String name,
        String street,
        String cep,
        String uf,
        String houseNumber,
        String neighbor,
        String complement,
        @Email
        String email,
        String phone,
        String document,
        String propertyType,
        String roofType
) {}
