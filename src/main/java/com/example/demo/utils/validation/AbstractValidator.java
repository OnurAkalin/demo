package com.example.demo.utils.validation;

import com.example.demo.utils.result.Result;
import com.example.demo.utils.result.SuccessResult;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractValidator<T> implements Validator<T> {
    protected final List<ValidationRule<T>> rules = new ArrayList<>();

    protected AbstractValidator() {
        configureRules();
    }

    /**
     * Override this method to add validation rules
     */
    protected abstract void configureRules();

    protected void addRule(ValidationRule<T> rule) {
        rules.add(rule);
    }

    @Override
    public Result validate(T object) {
        for (ValidationRule<T> rule : rules) {
            Result result = rule.check(object);
            if (!result.isSuccess()) {
                return result;
            }
        }
        return new SuccessResult();
    }
}