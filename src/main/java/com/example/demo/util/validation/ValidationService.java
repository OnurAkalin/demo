package com.example.demo.util.validation;

import java.util.Collection;

public interface ValidationService {

    <T> ValidationResult validate(T target,
                                  Collection<? extends BusinessValidator<? super T>> validators);
}
