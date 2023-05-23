package com.example.assesment.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class APIRequestException extends RuntimeException{
    private HttpStatus httpStatus;
    public APIRequestException(String message) {
        super(message);
    }
    public APIRequestException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
    public APIRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
