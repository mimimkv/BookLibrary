package com.example.booklibrary.exceptions;

public class BorrowNotFoundException extends Exception {
    public BorrowNotFoundException(String message) {
        super(message);
    }
}
