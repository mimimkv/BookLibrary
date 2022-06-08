package com.example.booklibrary.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message) {
        super(message);
    }
}
