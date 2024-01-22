package com.dreamix.overachievers.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Long id) {
        super(String.format("%s with ID %d is not found!", entity, id));
    }
    public EntityNotFoundException(String message) {
        super(message);
    }
}
