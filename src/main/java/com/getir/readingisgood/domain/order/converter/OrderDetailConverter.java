package com.getir.readingisgood.domain.order.converter;

import org.springframework.stereotype.Component;

import com.getir.readingisgood.domain.order.entity.OrderDetailEntity;
import com.getir.readingisgood.domain.order.model.OrderDetailDto;

@Component
public class OrderDetailConverter {
	public OrderDetailDto convertToDto(OrderDetailEntity entity) {
		return OrderDetailDto.builder().id(entity.getId()).bookName(entity.getBookName()).bookId(entity.getBookId())
				.price(entity.getPrice()).amount(entity.getAmount()).orderId(entity.getOrder().getId()).build();
	}
}
