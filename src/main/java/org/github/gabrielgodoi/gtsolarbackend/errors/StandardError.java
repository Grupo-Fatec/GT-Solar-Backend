package org.github.gabrielgodoi.gtsolarbackend.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {
    private String message;
    private Integer status;
    private String path;
    private LocalDateTime timestamp;
}
