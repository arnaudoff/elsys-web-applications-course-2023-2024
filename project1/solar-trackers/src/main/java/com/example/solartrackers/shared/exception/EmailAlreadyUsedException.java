package com.example.solartrackers.shared.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException() {
        super("Email is already used");
    }
}
