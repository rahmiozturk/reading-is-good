package com.getir.readingisgood.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
public class AuthorizationException extends BusinessException {

	private static final String CODE = "1000";
	private static final String DESCRIPTION = "Authorization Exception.";

	@Override
	public String getCode() {
		return CODE;
	}

	@Override
	public String getMessage() {
		return DESCRIPTION;
	}

	@Override
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
