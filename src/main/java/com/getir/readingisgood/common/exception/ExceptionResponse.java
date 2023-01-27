package com.getir.readingisgood.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(staticName = "build")
public class ExceptionResponse {
	private String code;
	private String message;
}
