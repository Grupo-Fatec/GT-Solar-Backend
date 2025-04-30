package org.github.gabrielgodoi.gtsolarbackend.enums;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    PLANNING("palnning"),
    EXECUTION("execution"),
    DONE("done"),
    CANCELED("canceled");

    private final String value;

    @JsonCreator
    public static StatusEnum fromValue(String value) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.value.equalsIgnoreCase(value)) {
                return statusEnum;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
