package com.getir.readingisgood.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.getir.readingisgood.domain.book.business.BookService;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;
import com.getir.readingisgood.domain.customer.business.CustomerService;
import com.getir.readingisgood.domain.customer.model.request.CreateCustomerRequest;
import com.getir.readingisgood.domain.order.business.OrderService;
import com.getir.readingisgood.domain.order.model.OrderBookDto;
import com.getir.readingisgood.domain.order.model.OrderDetailDto;
import com.getir.readingisgood.domain.order.model.OrderDto;
import com.getir.readingisgood.domain.order.model.request.CreateOrderRequest;
import com.getir.readingisgood.domain.order.model.request.DateIntervalRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderControllerTest {

	@Autowired
	private OrderService orderService;

	@Test

	public void createOrderAndgetOrderDetailByOrderId() {

		OrderBookDto orderDto = OrderBookDto.builder().amount(10).bookId(1L).build();
		CreateOrderRequest request = CreateOrderRequest.builder().bookList(List.of(orderDto)).customerId(1L).build();

		orderService.createOrder(request);

	}

	@Test
	public void filterByOrderTime() {
		DateIntervalRequest request = DateIntervalRequest.builder().startDate(new Date())
				.endDate(new Date(new Date().getTime() + (1000 * 60 * 60 * 24))).build();
		List<OrderDto> allOrders = orderService.getAllByOrderTimeDateBetween(request);
		assertEquals(allOrders, new ArrayList<OrderDto>());

	}

	@Test
	public void getOrderDetailByOrderId() {
		List<OrderDetailDto> orderDetail = orderService.getOrderDetailByOrderId(50L);
		assertEquals(orderDetail.size(),0L);

	}

}
