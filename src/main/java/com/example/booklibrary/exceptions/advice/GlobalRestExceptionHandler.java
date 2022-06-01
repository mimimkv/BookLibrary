package com.example.booklibrary.exceptions.advice;

import com.example.booklibrary.exceptions.BookNotFoundException;
import com.example.booklibrary.exceptions.ObjectNotFoundException;
import com.example.booklibrary.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalRestExceptionHandler {

    private static final String ERROR_TEMPLATE_MSG = "{ \"error\": \"%s\" }";

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleNotFound(UserNotFoundException e) {
        System.out.println("handle");
        String bodyOfResponse = String.format(ERROR_TEMPLATE_MSG, e.getMessage());
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }

    /*public ResponseEntity<Object> handleUserNotFound(UserNotFoundException e) {
        return new Re
    }*/
}
