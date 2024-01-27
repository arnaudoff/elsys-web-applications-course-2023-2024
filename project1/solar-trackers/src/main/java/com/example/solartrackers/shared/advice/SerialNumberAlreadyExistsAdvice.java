package com.example.solartrackers.shared.advice;

import com.example.solartrackers.shared.exception.SerialNumberAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SerialNumberAlreadyExistsAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SerialNumberAlreadyExistsException.class)
    public ResponseEntity<String> handleSerialNumberAlreadyExistsException(SerialNumberAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
