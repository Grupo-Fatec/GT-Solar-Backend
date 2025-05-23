package org.github.gabrielgodoi.gtsolarbackend.controller.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.github.gabrielgodoi.gtsolarbackend.errors.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> notFound(EntityNotFoundException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new StandardError(
                        e.getMessage(),
                        HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI(),
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(UnprocessableEntity.class)
    public ResponseEntity<StandardError> unprocessableEntity(EntityNotFoundException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                new StandardError(
                        e.getMessage(),
                        HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        request.getRequestURI(),
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(UnsupportedMediaType.class)
    public ResponseEntity<StandardError> unsupportedMediaType(EntityNotFoundException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
                new StandardError(
                        e.getMessage(),
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                        request.getRequestURI(),
                        LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<StandardError> alreadyExists(AlreadyExistsException e, HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new StandardError(
                        e.getMessage(),
                        HttpStatus.FORBIDDEN.value(),
                        request.getRequestURI(),
                        LocalDateTime.now()
                )
        );
    }
}
