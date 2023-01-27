package com.getir.readingisgood.common.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException e) {
		return new ResponseEntity<>(ExceptionResponse.build(e.getCode(), e.getMessage()), e.getStatus());
	}

	@ExceptionHandler({ ConstraintViolationException.class, BindException.class })
	public ResponseEntity<ExceptionResponse> handleValidationException(ConstraintViolationException e) {
		return new ResponseEntity<>(ExceptionResponse.build("400", e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
