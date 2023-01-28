package com.getir.readingisgood.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.getir.readingisgood.domain.customer.model.CustomerDto;
import com.getir.readingisgood.domain.customer.model.request.CreateCustomerRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void addCustomer() throws Exception {
		CreateCustomerRequest req = CreateCustomerRequest.builder().name("customer1").surname("surname1")
				.email("example@gmail.com").phoneNumber("0531-000").build();
		ResponseEntity<CustomerDto> response = restTemplate.postForEntity("/customer/create", req, CustomerDto.class);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

}