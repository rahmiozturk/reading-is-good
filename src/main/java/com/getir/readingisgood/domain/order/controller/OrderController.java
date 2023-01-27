package com.getir.readingisgood.domain.order.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgood.common.SoftwareComponent;
import com.getir.readingisgood.domain.order.api.OrderApi;
import com.getir.readingisgood.domain.order.business.OrderService;
import com.getir.readingisgood.domain.order.model.OrderDetailDto;
import com.getir.readingisgood.domain.order.model.OrderDto;
import com.getir.readingisgood.domain.order.model.request.CreateOrderRequest;
import com.getir.readingisgood.domain.order.model.request.DateIntervalRequest;

@RestController
@RequestMapping(value = "/order", consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }, produces = {
		APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
@SoftwareComponent(name = "OrderController", description = "Exposes end points to order process.", technologies = {
		"java", "rest", "https" })
public class OrderController implements OrderApi {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/create")
	public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequest createReq) {
		return new ResponseEntity<>(orderService.createOrder(createReq), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<List<OrderDetailDto>> getOrderDetailByOrderId(@PathVariable("id") Long orderId) {
		return new ResponseEntity<>(orderService.getOrderDetailByOrderId(orderId), HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<OrderDto>> filterByOrderTime(
			@Valid @RequestBody DateIntervalRequest dateIntervalRequest) {
		return ResponseEntity.ok(orderService.getAllByOrderTimeDateBetween(dateIntervalRequest));
	}

}
