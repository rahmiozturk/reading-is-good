package com.getir.readingisgood.domain.order.model;

import java.math.BigDecimal;
import java.util.Date;

import com.getir.readingisgood.domain.order.entity.OrderEntity.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

	private Long id;
	private String customerName;
	private BigDecimal totalPrice;
	private Date orderTime;
	private Integer totalAmount;
	private OrderStatus orderStatus;
}
