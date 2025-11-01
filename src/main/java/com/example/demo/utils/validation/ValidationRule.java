package com.example.demo.utils.validation;

import com.example.demo.utils.result.Result;

@FunctionalInterface
public interface ValidationRule<T> {
    Result check(T object);
}
