package org.github.gabrielgodoi.gtsolarbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    PLANNING(1),
    EXECUTION(2),
    DONE(3),
    CANCELED(4);

    @Getter
    private final Integer value;
}
