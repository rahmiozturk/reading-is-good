package com.getir.readingisgood.domain.order.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.getir.readingisgood.domain.order.model.OrderDetailDto;
import com.getir.readingisgood.domain.order.model.OrderDto;
import com.getir.readingisgood.domain.order.model.request.CreateOrderRequest;
import com.getir.readingisgood.domain.order.model.request.DateIntervalRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Order API")
public interface OrderApi {

	@ApiOperation(value = "", notes = "Create Order", nickname = "createOrder", tags = { "Order_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Create Order Response") })
	public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequest createReq);

	@ApiOperation(value = "", notes = "Get Order By Id", nickname = "getOrderById", tags = { "Order_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Order Response") })
	public ResponseEntity<List<OrderDetailDto>> getOrderDetailByOrderId(@PathVariable("id") Long orderId);

	@ApiOperation(value = "", notes = "Get Order CreationDate", nickname = "filterByCreationDate", tags = {
			"Order_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Order CreationDate Response") })
	public ResponseEntity<List<OrderDto>> filterByOrderTime(@Valid DateIntervalRequest dateIntervalRequest);

}
