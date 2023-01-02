package com.example.page.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestAPIExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handlerApiRequestException(IllegalArgumentException e) {
        RestApiException restApiException = new RestApiException(e.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(restApiException, HttpStatus.BAD_REQUEST);
    }

}
