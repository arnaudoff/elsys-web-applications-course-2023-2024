package com.example.solartrackers.shared.advice;

import com.example.solartrackers.shared.exception.EmailAlreadyUsedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EmailAlreadyUsedAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<String> handleEmailAlreadyUsedException(EmailAlreadyUsedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}