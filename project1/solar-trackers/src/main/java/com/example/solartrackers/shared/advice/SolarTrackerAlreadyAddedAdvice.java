package com.example.solartrackers.shared.advice;

import com.example.solartrackers.shared.exception.SolarTrackerAlreadyAddedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SolarTrackerAlreadyAddedAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SolarTrackerAlreadyAddedException.class)
    public ResponseEntity<String> handleSolarTrackerAlreadyAddedException(SolarTrackerAlreadyAddedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}