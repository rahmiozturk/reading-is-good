package com.getir.readingisgood.common.exception;

import org.springframework.http.HttpStatus;



@SuppressWarnings("serial")
public class EmailAlreadyExistsException extends BusinessException {

	private static final String CODE = "8001";
	private static final String DESCRIPTION = "Email Already Exists.";

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
