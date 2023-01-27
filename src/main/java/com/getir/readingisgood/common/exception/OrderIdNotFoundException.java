package com.getir.readingisgood.common.exception;

import org.springframework.http.HttpStatus;



@SuppressWarnings("serial")
public class OrderIdNotFoundException extends BusinessException {

	private static final String CODE = "7000";
	private static final String DESCRIPTION = "Order Id not founded.";

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
