package com.example.demo.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UIMessages {
    public static final String SUCCESS = "Operation successful.";
    public static final String ERROR = "Operation failed.";
    public static final String NOT_FOUND_DATA = "No suitable data found.";
    public static final String UNKNOWN_ERROR = "An unknown error occurred.";
}