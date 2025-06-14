package org.github.gabrielgodoi.gtsolarbackend.dto.person;

import jakarta.validation.constraints.*;

public record EngineerDto(
        String id,
        @Min(4)
        String name,
        @Email
        String email,
        String crea,
        Double valuePerKwp
) {
}
