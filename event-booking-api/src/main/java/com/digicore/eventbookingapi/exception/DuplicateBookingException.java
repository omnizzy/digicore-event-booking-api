package com.digicore.eventbookingapi.exception;

public class DuplicateBookingException extends RuntimeException{
    public DuplicateBookingException(String message) {
        super(message);
    }
}
