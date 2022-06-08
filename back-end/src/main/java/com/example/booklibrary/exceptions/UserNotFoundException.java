package com.example.booklibrary.exceptions;

public class UserNotFoundException extends ObjectNotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
