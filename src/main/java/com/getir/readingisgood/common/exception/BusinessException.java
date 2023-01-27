package com.getir.readingisgood.common.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public abstract class BusinessException extends RuntimeException {

	public abstract String getCode();

	public HttpStatus getStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}
}