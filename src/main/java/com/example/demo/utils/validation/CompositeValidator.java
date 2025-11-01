package com.example.demo.utils.validation;

import com.example.demo.utils.result.Result;
import com.example.demo.utils.result.SuccessResult;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CompositeValidator<T> implements Validator<T> {
    private final List<ValidationRule<T>> rules = new ArrayList<>();

    public CompositeValidator<T> addRule(ValidationRule<T> rule) {
        rules.add(rule);
        return this;
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
