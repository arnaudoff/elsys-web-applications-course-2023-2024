package com.example.solartrackers.shared.exception;

public class LocationAlreadyAddedException extends RuntimeException {
    public LocationAlreadyAddedException() {
        super("Location already added");
    }
}
