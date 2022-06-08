package com.example.booklibrary.controller;

import com.example.booklibrary.exceptions.BookNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BookNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(BookNotFoundException ex, WebRequest request) {
        String bodyOfResponse = "{ \"error\": \"" + ex.getMessage() + "\" }";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        //String bodyOfResponse = "{ \"error\": \"" + ex.getMessage() + "\" }";
        //return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
            .getAllErrors()
            .forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                errors.put(fieldName, message);
            });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(SQLIntegrityConstraintViolationException e) {
        String bodyOfResponse = "{ \"error\": \"" + e.getMessage() + "\" }";
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }
}
