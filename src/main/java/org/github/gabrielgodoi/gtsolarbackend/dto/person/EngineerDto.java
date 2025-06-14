package org.github.gabrielgodoi.gtsolarbackend.dto.person;

import jakarta.validation.constraints.*;

public record EngineerDto(
        String id,
        @NotNull
        String name,
        @Email
        String email,
        String crea,
        @NotNull
        Double valuePerKwh
) {
}
