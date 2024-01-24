package com.example.solartrackers.shared.advice;

import com.example.solartrackers.shared.exception.SolarTrackerIsTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SolarTrackerIsTakenAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SolarTrackerIsTakenException.class)
    public ResponseEntity<String> handleSolarTrackerIsTakenException(SolarTrackerIsTakenException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}