package org.github.gabrielgodoi.gtsolarbackend.errors;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
