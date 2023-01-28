package com.getir.readingisgood.domain.order.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderBookDto {
	@NotNull
	private Long bookId;
	
	@Min(1)
	@Max(100)
	private Integer amount;
}
