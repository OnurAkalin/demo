package com.example.demo.exception;

import com.example.demo.constant.UIMessages;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessException {

    public NotFoundException() {
        super(UIMessages.NOT_FOUND_DATA, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
