package com.jjuarez.gila.exception;

public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(final String message) {
        super(message);
    }
}
