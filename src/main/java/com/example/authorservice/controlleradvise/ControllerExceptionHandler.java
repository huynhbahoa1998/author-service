package com.example.authorservice.controlleradvise;

import com.example.authorservice.dto.AuthorResponse;
import com.example.authorservice.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public AuthorResponse resourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        return new AuthorResponse(null, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AuthorResponse globalException(Exception exception, WebRequest request) {
        return new AuthorResponse(null, exception.getMessage());
    }
}
