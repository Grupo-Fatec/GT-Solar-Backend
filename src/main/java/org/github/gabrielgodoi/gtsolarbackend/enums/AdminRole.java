package org.github.gabrielgodoi.gtsolarbackend.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminRole {
    SELLER("seller"),
    MANAGER("manager"),
    SUPPORT("suport"),
    ADMIN("admin"),
    SUPER_ADMIN("super_admin");

    private final String value;

    @JsonCreator
    public static AdminRole fromValue(String value) {
        for (AdminRole role : AdminRole.values()) {
            if (role.value.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + value);
    }
}
