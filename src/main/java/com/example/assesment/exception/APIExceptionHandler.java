package com.example.assesment.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(value =  {APIRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(APIRequestException e) {
        APIException exc = new APIException(e.getMessage(), e.getHttpStatus(), ZonedDateTime.now());
        return  new ResponseEntity<>(exc, e.getHttpStatus());
    }
}
