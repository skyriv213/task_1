package com.example.page.exception;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@AllArgsConstructor
public class RestApiException {
    private String errorMessage;
    private HttpStatus httpStatus;
}
