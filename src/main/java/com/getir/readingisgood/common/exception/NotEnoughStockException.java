package com.getir.readingisgood.common.exception;

import org.springframework.http.HttpStatus;



@SuppressWarnings("serial")
public class NotEnoughStockException extends BusinessException {

	private static final String CODE = "9001";
	private static final String DESCRIPTION = "There is not enough stock for the book.";

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
