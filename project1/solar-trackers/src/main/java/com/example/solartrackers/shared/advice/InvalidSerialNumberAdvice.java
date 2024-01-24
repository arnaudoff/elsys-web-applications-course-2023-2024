package com.example.solartrackers.shared.advice;

import com.example.solartrackers.shared.exception.InvalidSerialNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InvalidSerialNumberAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidSerialNumberException.class)
    public ResponseEntity<String> handleInvalidSerialNumberException(InvalidSerialNumberException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}