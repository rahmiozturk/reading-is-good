package com.getir.readingisgood.domain.customer.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.getir.readingisgood.common.model.ApiPagingResponse;
import com.getir.readingisgood.domain.customer.model.CustomerDto;
import com.getir.readingisgood.domain.customer.model.CustomerReportDto;
import com.getir.readingisgood.domain.customer.model.request.CreateCustomerRequest;
import com.getir.readingisgood.domain.order.model.OrderDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Customer API")
public interface CustomerApi {

	@ApiOperation(value = "", notes = "Create Customer", nickname = "createCustomer", tags = { "Customer_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Create Customer Response") })
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest createReq);

	@ApiOperation(value = "", notes = "Get Customers", nickname = "getCustomers", tags = { "Customer_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Customers Response") })
	public ResponseEntity<ApiPagingResponse<CustomerDto>> getCustomers(
			@RequestParam(value = "PAGE", defaultValue = "0") int page,
			@RequestParam(value = "SIZE", defaultValue = "10") int size);

	@ApiOperation(value = "", notes = "Get Customer By Id", nickname = "getCustomerById", tags = { "Customer_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Customer By Id Response") })
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long customerId);

	@ApiOperation(value = "", notes = "Get Customer Orders", nickname = "getAllOrders", tags = { "Customer_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Customer Orders Response") })
	public ResponseEntity<List<OrderDto>> getAllOrders(@PathVariable("id") Long customerId);

	@ApiOperation(value = "", notes = "Get Customer Stats", nickname = "getCustomerStats", tags = { "Customer_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Customer Report Response") })
	public ResponseEntity<List<CustomerReportDto>> getCustomerStats(@PathVariable("id") Long customerId);
}
