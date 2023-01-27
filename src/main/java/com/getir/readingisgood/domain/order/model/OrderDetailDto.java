package com.getir.readingisgood.domain.order.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailDto {

	private Long id;
	private String bookName;
	private Long bookId;
	private BigDecimal price;
	private Integer amount;
	private Long orderId;

}
