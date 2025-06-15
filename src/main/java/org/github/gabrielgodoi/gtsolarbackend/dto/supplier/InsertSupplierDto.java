package org.github.gabrielgodoi.gtsolarbackend.dto.supplier;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record InsertSupplierDto(
        @Min(3)
        String name,
        @Email
        String email,
        String deliveryDate
) {
}
