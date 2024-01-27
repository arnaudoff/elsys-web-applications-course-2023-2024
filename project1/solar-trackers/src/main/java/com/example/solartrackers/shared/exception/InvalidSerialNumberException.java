package com.example.solartrackers.shared.exception;

public class InvalidSerialNumberException extends RuntimeException {
    public InvalidSerialNumberException() {
        super("Invalid serial number");
    }
}
