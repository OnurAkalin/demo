package com.example.demo.util.validation;

public interface ValidationRule<T> {
    ValidationRuleGroup group();

    Class<T> type();

    void validate(T target);
}
