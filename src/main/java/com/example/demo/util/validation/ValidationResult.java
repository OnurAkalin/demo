package com.example.demo.util.validation;

import java.util.List;

public record ValidationResult(List<ValidationError> errors) {

    public ValidationResult {
        errors = errors == null ? List.of() : List.copyOf(errors);
    }

    public static ValidationResult valid() {
        return new ValidationResult(List.of());
    }

    public static ValidationResult invalid(ValidationError... errors) {
        return invalid(List.of(errors));
    }

    public static ValidationResult invalid(List<ValidationError> errors) {

        if (errors == null || errors.isEmpty()) {
            throw new IllegalArgumentException("Validation errors must not be empty");
        }

        return new ValidationResult(errors);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public boolean isInvalid() {
        return !isValid();
    }
}
