package com.getir.readingisgood.domain.order.converter;

import org.springframework.stereotype.Component;

import com.getir.readingisgood.domain.order.entity.OrderEntity;
import com.getir.readingisgood.domain.order.model.OrderDto;

@Component
public class OrderConverter {
	public OrderDto convertToDto(OrderEntity entity) {
		return OrderDto.builder().id(entity.getId()).customerName(entity.getCustomer().getName())
				.totalAmount(entity.getTotalAmount()).totalPrice(entity.getTotalPrice())
				.orderTime(entity.getOrderTime()).orderStatus(entity.getOrderStatus()).build();
	}
}
