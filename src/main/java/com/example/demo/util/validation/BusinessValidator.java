package com.example.demo.util.validation;

@FunctionalInterface
public interface BusinessValidator<T> {
    ValidationResult validate(T target);
}