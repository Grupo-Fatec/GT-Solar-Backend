package org.github.gabrielgodoi.gtsolarbackend.dto.person;

import jakarta.validation.constraints.Email;

public record InstallerDto(
        String id,
        String name,
        @Email
        String email,
        Double pricePerKwp,
        String availableDays,
        boolean isAvailable
) {
}
