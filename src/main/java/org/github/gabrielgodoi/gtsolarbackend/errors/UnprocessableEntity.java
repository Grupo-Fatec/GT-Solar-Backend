package org.github.gabrielgodoi.gtsolarbackend.errors;

public class UnprocessableEntity extends RuntimeException {
    public UnprocessableEntity(String message) {
        super(message);
    }
}
