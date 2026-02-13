package com.example.demo.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private final String code;

    public ValidationException(String message) {
        super(message);
        this.code = "VALIDATION_ERROR";
    }
}
