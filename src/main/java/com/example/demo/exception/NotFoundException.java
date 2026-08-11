package com.example.demo.exception;

import com.example.demo.constant.UIMessages;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super(UIMessages.NOT_FOUND_DATA);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
