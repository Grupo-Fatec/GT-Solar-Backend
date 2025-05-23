package org.github.gabrielgodoi.gtsolarbackend.enums;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;


@Getter
@AllArgsConstructor
public enum BudgetStatus {
    PENDING("pending"),
    SENDED("sended"),
    APPROVED("approved"),
    REJECTED("rejected"),
    EXPIRED("expired");

    private final String value;

    @JsonCreator
    public static BudgetStatus fromValue(String value){
        for (BudgetStatus role : BudgetStatus.values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
