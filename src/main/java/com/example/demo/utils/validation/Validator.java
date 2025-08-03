package com.example.demo.utils.validation;

import com.example.demo.utils.result.Result;

public interface Validator<T> {
    Result validate(T object);
}
