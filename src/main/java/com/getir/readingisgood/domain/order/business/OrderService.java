package com.getir.readingisgood.domain.order.business;

import java.util.List;

import com.getir.readingisgood.domain.order.model.OrderDetailDto;
import com.getir.readingisgood.domain.order.model.OrderDto;
import com.getir.readingisgood.domain.order.model.request.CreateOrderRequest;
import com.getir.readingisgood.domain.order.model.request.DateIntervalRequest;

public interface OrderService {

	OrderDto createOrder(CreateOrderRequest createReq);

	List<OrderDetailDto> getOrderDetailByOrderId(Long orderId);

	List<OrderDto> getOrdersByCustomerId(Long customerId);

	List<OrderDto> getAllByOrderTimeDateBetween(DateIntervalRequest dateIntervalRequest);

}
