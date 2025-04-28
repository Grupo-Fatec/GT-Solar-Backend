package org.github.gabrielgodoi.gtsolarbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    PAID(1),
    WAITING(2),
    APPROVED(3),
    OVERDUE(4),
    PENDING(5);

    @Getter
    private final Integer value;
}
