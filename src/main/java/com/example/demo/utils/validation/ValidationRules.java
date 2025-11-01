package com.example.demo.utils.validation;

import com.example.demo.utils.result.ErrorResult;
import com.example.demo.utils.result.SuccessResult;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public class ValidationRules {

    public static <T> ValidationRule<T> notNull(Function<T, Object> fieldExtractor, String fieldName) {
        return object -> {
            Object value = fieldExtractor.apply(object);
            if (value == null) {
                return new ErrorResult(fieldName + " cannot be null");
            }
            return new SuccessResult();
        };
    }

    public static <T> ValidationRule<T> notEmpty(Function<T, String> fieldExtractor, String fieldName) {
        return object -> {
            String value = fieldExtractor.apply(object);
            if (!StringUtils.hasText(value)) {
                return new ErrorResult(fieldName + " cannot be empty");
            }
            return new SuccessResult();
        };
    }

    public static <T> ValidationRule<T> notEmptyCollection(Function<T, Collection<?>> fieldExtractor, String fieldName) {
        return object -> {
            Collection<?> value = fieldExtractor.apply(object);
            if (value == null || value.isEmpty()) {
                return new ErrorResult(fieldName + " cannot be empty");
            }
            return new SuccessResult();
        };
    }

    public static <T> ValidationRule<T> minLength(Function<T, String> fieldExtractor, String fieldName, int min) {
        return object -> {
            String value = fieldExtractor.apply(object);
            if (value != null && value.length() < min) {
                return new ErrorResult(fieldName + " must be at least " + min + " characters");
            }
            return new SuccessResult();
        };
    }

    public static <T> ValidationRule<T> maxLength(Function<T, String> fieldExtractor, String fieldName, int max) {
        return object -> {
            String value = fieldExtractor.apply(object);
            if (value != null && value.length() > max) {
                return new ErrorResult(fieldName + " must be at most " + max + " characters");
            }
            return new SuccessResult();
        };
    }

    public static <T> ValidationRule<T> matches(Function<T, String> fieldExtractor, String fieldName, String regex) {
        return object -> {
            String value = fieldExtractor.apply(object);
            if (value != null && !value.matches(regex)) {
                return new ErrorResult(fieldName + " format is invalid");
            }
            return new SuccessResult();
        };
    }

    public static <T> ValidationRule<T> custom(Predicate<T> predicate, String errorMessage) {
        return object -> {
            if (!predicate.test(object)) {
                return new ErrorResult(errorMessage);
            }
            return new SuccessResult();
        };
    }

    public static <T, V extends Comparable<V>> ValidationRule<T> min(Function<T, V> fieldExtractor, String fieldName, V minValue) {
        return object -> {
            V value = fieldExtractor.apply(object);
            if (value != null && value.compareTo(minValue) < 0) {
                return new ErrorResult(fieldName + " must be at least " + minValue);
            }
            return new SuccessResult();
        };
    }

    public static <T, V extends Comparable<V>> ValidationRule<T> max(Function<T, V> fieldExtractor, String fieldName, V maxValue) {
        return object -> {
            V value = fieldExtractor.apply(object);
            if (value != null && value.compareTo(maxValue) > 0) {
                return new ErrorResult(fieldName + " must be at most " + maxValue);
            }
            return new SuccessResult();
        };
    }
}