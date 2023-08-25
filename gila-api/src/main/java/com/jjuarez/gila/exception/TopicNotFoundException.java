package com.jjuarez.gila.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException(final String message) {
        super(message);
    }
}
