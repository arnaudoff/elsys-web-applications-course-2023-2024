package com.example.solartrackers.shared.exception;

public class SerialNumberAlreadyExistsException extends RuntimeException {
    public SerialNumberAlreadyExistsException() {
        super("Serial number already exists");
    }
}
