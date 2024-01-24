package com.example.solartrackers.shared.advice;

import com.example.solartrackers.shared.exception.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InvalidIdAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<String> handleInvalidSerialNumberException(InvalidIdException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}