package org.github.gabrielgodoi.gtsolarbackend.dto.person;

public record InstallerDto(
        String id,
        String name,
        String email,
        Double pricePerKwp,
        String availableDays,
        boolean isAvailable
) {
}
