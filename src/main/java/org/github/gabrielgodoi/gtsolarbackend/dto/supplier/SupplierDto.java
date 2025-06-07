package org.github.gabrielgodoi.gtsolarbackend.dto.supplier;

import java.time.LocalDateTime;

public record SupplierDto(
        String id,
        String name,
        LocalDateTime deliveryDate
) {
}
