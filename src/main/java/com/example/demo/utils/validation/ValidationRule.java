package com.example.demo.utils.validation;

public interface ValidationRule<T> {
    ValidationRuleGroup group();

    Class<T> type();

    void validate(T target);
}
