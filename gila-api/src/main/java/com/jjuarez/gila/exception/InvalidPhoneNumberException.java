package com.jjuarez.gila.exception;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(final String message) {
        super(message);
    }
}
