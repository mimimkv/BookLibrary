package com.example.booklibrary.exceptions;

public class AuthorNotFoundException extends ObjectNotFoundException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
