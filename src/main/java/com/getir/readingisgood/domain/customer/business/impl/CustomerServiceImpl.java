package com.getir.readingisgood.domain.customer.business.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.getir.readingisgood.domain.customer.business.CustomerService;
import com.getir.readingisgood.domain.customer.converter.CustomerConverter;
import com.getir.readingisgood.domain.customer.data.CustomerDataService;
import com.getir.readingisgood.domain.customer.entity.CustomerEntity;
import com.getir.readingisgood.domain.customer.model.CustomerDto;
import com.getir.readingisgood.domain.customer.model.request.CreateCustomerRequest;
import com.getir.readingisgood.domain.order.business.OrderService;
import com.getir.readingisgood.domain.order.model.OrderDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerDataService customerDataService;
	private final CustomerConverter customerConverter;
	private final OrderService orderService;

	public CustomerServiceImpl(CustomerDataService customerDataService, CustomerConverter customerConverter,
			OrderService orderService) {
		this.customerDataService = customerDataService;
		this.customerConverter = customerConverter;
		this.orderService = orderService;
	}

	@Override
	public CustomerDto createCustomer(CreateCustomerRequest createReq) {
		customerDataService.checkEmailAlreadyExists(createReq.getEmail());
		CustomerEntity entity = customerDataService.saveCustomer(customerConverter.convertToEntity(createReq));
		return customerConverter.convertToDto(entity);
	}

	@Override
	public CustomerDto getCustomerById(Long customerId) {
		CustomerEntity entity = customerDataService.getCustomerById(customerId);
		return customerConverter.convertToDto(entity);
	}

	@Override
	public List<CustomerDto> getCustomers(int page, int size) {
		List<CustomerEntity> entityList = customerDataService.getCustomers(page, size);
		List<CustomerDto> dtoList = entityList.stream().map(entity -> customerConverter.convertToDto(entity))
				.collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public List<OrderDto> getAllOrders(Long customerId) {
		return orderService.getOrdersByCustomerId(customerId);
	}

}
