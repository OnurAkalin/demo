package com.example.demo.exception;

import com.example.demo.constant.UIMessages;
import com.example.demo.util.result.ErrorDataResult;
import com.example.demo.util.result.ErrorResult;
import com.example.demo.util.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> exception(HttpServletRequest request, HandlerMethod handlerMethod, Exception exception) {
        String controller = "Unknown";
        String method = "Unknown";

        if (handlerMethod != null) {
            controller = handlerMethod.getBeanType().getSimpleName();
            method = handlerMethod.getMethod().getName();

        }

        log.error("""
                      Unhandled exception occurred at
                       \
                      URL: {}
                       \
                      Method: {}
                       \
                      Controller: {}
                       \
                      Method: {}
                       \
                              Exception:
                """,
                request.getRequestURL(),
                request.getMethod(),
                controller,
                method,
                exception);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResult(UIMessages.UNKNOWN_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.putIfAbsent(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDataResult<>(errors, UIMessages.VALIDATION_ERROR));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Result> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn("Invalid parameter value. parameter={}, requiredType={}",
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType() : "Unknown"
        );
        String errorMessage = String.format("Invalid value for parameter '%s'", ex.getName());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResult(errorMessage));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Result> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.warn("Invalid request body -> {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResult("Request body is missing or invalid."));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result> handleBusinessException(BusinessException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ErrorResult(ex.getMessage()));

    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<Result> handleLoginFailure() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResult(UIMessages.LOGIN_FAILURE));
    }
}
