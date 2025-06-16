package org.github.gabrielgodoi.gtsolarbackend.dto.client;


import java.time.LocalDateTime;

public record ClientDto(
        String id,
        String name,
        String document,
        String email,
        String phone,
        String street,
        String houseNumber,
        String complement,
        String neighbor,
        String city,
        String uf,
        String cep,
        String roofType,
        String propertyType,
        String observations,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {}
