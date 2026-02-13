package com.example.demo.util.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Validator {
    private final List<ValidationRule<?>> rules;

    @SuppressWarnings("unchecked")
    public <T> void validate(T target, ValidationRuleGroup group) {

        rules.stream()
                .filter(r -> r.group() == group)
                .forEach(r -> ((ValidationRule<T>) r).validate(target));
    }
}
