package com.example.page.exception;

import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
public class RestApiException {
    private String errorMessage;
    private HttpStatus httpStatus;
}
