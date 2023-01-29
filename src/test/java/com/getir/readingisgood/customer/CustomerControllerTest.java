package com.getir.readingisgood.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.getir.readingisgood.common.exception.CustomerIdNotFoundException;
import com.getir.readingisgood.common.exception.EmailAlreadyExistsException;
import com.getir.readingisgood.common.model.ApiPagingResponse;
import com.getir.readingisgood.domain.customer.business.impl.CustomerServiceImpl;
import com.getir.readingisgood.domain.customer.data.CustomerDataService;
import com.getir.readingisgood.domain.customer.entity.CustomerEntity;
import com.getir.readingisgood.domain.customer.model.CustomerDto;
import com.getir.readingisgood.domain.customer.model.request.CreateCustomerRequest;
import com.getir.readingisgood.domain.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerControllerTest {

	@InjectMocks
	CustomerDataService customerDataService;

	@Autowired
	CustomerServiceImpl customerService;

	@Mock
	CustomerRepository customerRepository;

	private static CustomerDto customerDto;
	private static CustomerEntity customerEntity;

	@BeforeEach
	public void init() {
		customerDto = CustomerDto.builder().email("customer1@gmail.com").name("cusName1").surname("cusSurname1")
				.phoneNumber("0555333").build();

		customerEntity = CustomerEntity.builder().email("customer1@gmail.com").name("cusName1").surname("cusSurname1")
				.phoneNumber("0555333").id(1L).build();
	}

	@Test
	@Order(1)
	public void saveConsumer_whenEmailAlreadyExists_thenThrowCustomerAlreadyException() {

		when(customerRepository.findByEmail(customerEntity.getEmail())).thenReturn(Optional.of(customerEntity));

		assertThrows(EmailAlreadyExistsException.class, () -> customerDataService.saveCustomer(customerEntity));
	}

	@Test
	@Order(2)
	public void saveConsumer_thenReturnSuccessResponse() {

		when(customerDataService.saveCustomer(customerEntity)).thenReturn(customerEntity);

		CustomerEntity saveCustomer = customerDataService.saveCustomer(customerEntity);
		assertNotNull(saveCustomer);
		assertEquals(customerDto.getEmail(), saveCustomer.getEmail());
	}

	@Test
	@Order(3)
	public void getCustomerById_whenCustomerNotFound_thenCustomerIdNotFoundException() {
		when(customerRepository.findById(any())).thenReturn(Optional.empty());

		assertThrows(CustomerIdNotFoundException.class,
				() -> customerDataService.getCustomerById(customerEntity.getId()));
	}

	@Test
	@Order(4)
	public void createCustomerRequest() {
		CreateCustomerRequest req = CreateCustomerRequest.builder().email("customer1@gmail.com").name("cusName1")
				.surname("cusSurname1").phoneNumber("0555333").build();

		CustomerDto createCustomer = customerService.createCustomer(req);
		assertEquals(createCustomer.getEmail(), customerEntity.getEmail());
	}

	@Test
	@Order(5)
	public void getCustomerById() {

		CustomerDto customerById = customerService.getCustomerById(1L);
		assertEquals(customerById.getId(), customerEntity.getId());
	}

	@Test
	@Order(6)
	public void getCustomerAllPageAble() {
		int page = 0;
		int size = 10;
		ApiPagingResponse<CustomerDto> customers = customerService.getCustomers(page, size);
		assertEquals(customers.getCurrentPage(), page);
	}

}