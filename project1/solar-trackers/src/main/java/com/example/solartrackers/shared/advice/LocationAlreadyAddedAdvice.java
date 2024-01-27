package com.example.solartrackers.shared.advice;

import com.example.solartrackers.shared.exception.LocationAlreadyAddedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LocationAlreadyAddedAdvice {
    @ExceptionHandler(LocationAlreadyAddedException.class)
    public ResponseEntity<String> handleLocationAlreadyAddedException(LocationAlreadyAddedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}