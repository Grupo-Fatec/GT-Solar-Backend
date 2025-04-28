package org.github.gabrielgodoi.gtsolarbackend.enums;
import lombok.*;


@Getter
@AllArgsConstructor
public enum BudgetStatus {
    PENDING(1),
    SENDED(2),
    APROVED(3),
    REJECTED(4),
    EXPIRED(5);
    @Getter
    private final Integer value;
}
