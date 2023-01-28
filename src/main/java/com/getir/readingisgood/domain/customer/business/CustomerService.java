package com.getir.readingisgood.domain.customer.business;

import java.util.List;

import com.getir.readingisgood.common.model.ApiPagingResponse;
import com.getir.readingisgood.domain.customer.model.CustomerDto;
import com.getir.readingisgood.domain.customer.model.CustomerReportDto;
import com.getir.readingisgood.domain.customer.model.request.CreateCustomerRequest;
import com.getir.readingisgood.domain.order.model.OrderDto;

public interface CustomerService {

	CustomerDto getCustomerById(Long id);

	CustomerDto createCustomer(CreateCustomerRequest createReq);

	ApiPagingResponse<CustomerDto> getCustomers(int page, int size);

	List<OrderDto> getAllOrders(Long customerId);

	List<CustomerReportDto> getStatsReport(Long customerId);

}
