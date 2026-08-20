package com.example.demo.util.validation;

import java.util.Map;
import java.util.Objects;

public record ValidationError(String code, String field, Map<String, Object> parameters) {

    public ValidationError {
        Objects.requireNonNull(code, "code must not be null");

        if (code.isBlank()) {
            throw new IllegalArgumentException("code must not be blank");
        }

        parameters = parameters == null ? Map.of() : Map.copyOf(parameters);
    }

    public static ValidationError of(String code) {
        return new ValidationError(code, null, Map.of());
    }

    public static ValidationError of(String code, String field) {
        return new ValidationError(code, field, Map.of());
    }

    public static ValidationError of(String code, String field, Map<String, Object> parameters) {
        return new ValidationError(code, field, parameters);
    }
}
