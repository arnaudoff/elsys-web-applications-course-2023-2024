package com.example.solartrackers.shared.exception;

public class SolarTrackerIsTakenException extends RuntimeException {
    public SolarTrackerIsTakenException() {
        super("Solar tracker is already taken");
    }
}
