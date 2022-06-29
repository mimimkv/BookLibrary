package com.example.booklibrary.exceptions;

public class BookNotFoundException extends ObjectNotFoundException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
