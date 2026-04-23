package com.digicore.eventbookingapi.exception;

public class EventClosedException extends RuntimeException{
    public EventClosedException(String message) {
        super(message);
    }
}
