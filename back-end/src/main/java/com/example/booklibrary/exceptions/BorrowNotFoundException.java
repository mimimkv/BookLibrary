package com.example.booklibrary.exceptions;

public class BorrowNotFoundException extends ObjectNotFoundException {
    public BorrowNotFoundException(String message) {
        super(message);
    }
}
