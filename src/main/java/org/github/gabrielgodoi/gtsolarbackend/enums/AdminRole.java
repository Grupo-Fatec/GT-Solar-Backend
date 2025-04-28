package org.github.gabrielgodoi.gtsolarbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminRole {
    SELLER(1),
    MANAGER(2),
    SUPPORT(3),
    ADMIN(4),
    SUPER_ADMIN(5);

    @Getter
    private final Integer value;
}
