package com.example.booklibrary.exceptions;

import java.util.NoSuchElementException;

public class BookNotFoundException extends ObjectNotFoundException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
