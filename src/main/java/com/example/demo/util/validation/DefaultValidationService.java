package com.example.demo.util.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class DefaultValidationService implements ValidationService {

    @Override
    public <T> ValidationResult validate(T target, Collection<? extends BusinessValidator<? super T>> validators) {

        Objects.requireNonNull(target, "target must not be null");
        Objects.requireNonNull(validators, "validators must not be null");

        List<ValidationError> errors = new ArrayList<>();

        for (BusinessValidator<? super T> validator : validators) {
            Objects.requireNonNull(validator, "validator must not be null");

            ValidationResult result = Objects.requireNonNull(validator.validate(target), "validator result must not be null");

            errors.addAll(result.errors());
        }

        return errors.isEmpty() ? ValidationResult.valid() : ValidationResult.invalid(errors);
    }
}
