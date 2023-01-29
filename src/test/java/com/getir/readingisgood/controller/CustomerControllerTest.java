package com.getir.readingisgood.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.getir.readingisgood.common.model.ApiPagingResponse;
import com.getir.readingisgood.domain.book.model.BookDto;
import com.getir.readingisgood.domain.customer.business.CustomerService;
import com.getir.readingisgood.domain.customer.model.CustomerDto;
import com.getir.readingisgood.domain.customer.model.CustomerReportDto;
import com.getir.readingisgood.domain.customer.model.request.CreateCustomerRequest;
import com.getir.readingisgood.domain.order.model.OrderDto;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private CustomerService customerService;

	@BeforeAll
	@Transactional
	public void insertDummyData() {

		customerService.createCustomer(CreateCustomerRequest.builder().email("example1@gmail.com").name("cusName1")
				.surname("cusSurname1").phoneNumber("0533331").build());
		customerService.createCustomer(CreateCustomerRequest.builder().email("example2@gmail.com").name("cusName2")
				.surname("cusSurname2").phoneNumber("0533332").build());
	}

	@Test
	@WithMockUser(username = "5fc03087-d265-11e7-b8c6-83e29cd24f4c", password = "userpass", roles = { "ADMIN" })
	public void whenCreateCustomerThenReturnSuccessAndExpectedEmailIsCorrect() throws Exception {
		CreateCustomerRequest request = CreateCustomerRequest.builder().email("example3@gmail.com").name("cusName2")
				.surname("cusSurname2").phoneNumber("0533332").build();

		mockMvc.perform(post("/customer/admin/create").contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.email").value("example3@gmail.com"));
	}

	@Test
	@WithMockUser(username = "07344d08-ec0d-11ec-8ea0-0242ac120002", password = "userpass", roles = { "USER" })
	public void customerCreate() throws Exception {
		CreateCustomerRequest request = CreateCustomerRequest.builder().email("example3@gmail.com").name("cusName2")
				.surname("cusSurname2").phoneNumber("0533332").build();

		mockMvc.perform(post("/customer/admin/create").contentType(APPLICATION_JSON_VALUE)
				.accept(APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isForbidden());
	}

	@Test
	public void getCustomers() {
		int page = 0;
		int size = 10;
		ApiPagingResponse<CustomerDto> customer = customerService.getCustomers(page, size);
		assertEquals(customer.getCurrentPage(), page);
	}

	@Test
	public void getStatsReport() {

		List<CustomerReportDto> statsReport = customerService.getStatsReport(1L);
		assertEquals(statsReport, new ArrayList<CustomerReportDto>());

	}

	@Test
	public void getAllOrders() {

		List<OrderDto> allOrders = customerService.getAllOrders(1L);
		assertEquals(allOrders, new ArrayList<OrderDto>());

	}

}
