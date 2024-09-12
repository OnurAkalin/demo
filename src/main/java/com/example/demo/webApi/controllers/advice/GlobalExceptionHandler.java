package com.example.demo.webApi.controllers.advice;

import com.example.demo.common.constants.UIMessages;
import com.example.demo.common.result.ErrorResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResult> exception(HttpServletRequest request, HandlerMethod handlerMethod, Exception e) {
		log.error("Exception occurred at\n URL: {}\n Method: {}\n Controller: {}\n Method: {}",
				request.getRequestURL(),
				request.getMethod(),
				handlerMethod.getBeanType().getSimpleName(),
				handlerMethod.getMethod().getName(),
				e);

		return ResponseEntity.ok(new ErrorResult(UIMessages.UNKNOWN_ERROR));
	}
}
