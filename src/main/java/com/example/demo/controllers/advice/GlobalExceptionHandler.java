package com.example.demo.controllers.advice;

import com.example.demo.constants.UIMessages;
import com.example.demo.utils.result.ErrorResult;
import com.example.demo.utils.result.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> exception(HttpServletRequest request, HandlerMethod handlerMethod, Exception exception) {
        log.error("""
                        Exception occurred at
                         \
                        URL: {}
                         \
                        Method: {}
                         \
                        Controller: {}
                         \
                        Method: {}
                  """,
                request.getRequestURL(),
                request.getMethod(),
                handlerMethod.getBeanType().getSimpleName(),
                handlerMethod.getMethod().getName(),
                exception);

        return ResponseEntity.ok(new ErrorResult(UIMessages.UNKNOWN_ERROR));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResult(errors.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Result> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorMessage = String.format("Invalid value for parameter '%s'.", ex.getName());
        return ResponseEntity.badRequest().body(new ErrorResult(errorMessage));
    }
}
