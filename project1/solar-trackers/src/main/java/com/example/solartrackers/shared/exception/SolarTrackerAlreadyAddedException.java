package com.example.solartrackers.shared.exception;

public class SolarTrackerAlreadyAddedException extends RuntimeException {
    public SolarTrackerAlreadyAddedException() {
        super("Solar tracker already added to location");
    }
}
