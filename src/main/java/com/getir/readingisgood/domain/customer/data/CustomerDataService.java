package com.getir.readingisgood.domain.customer.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getir.readingisgood.common.exception.CustomerIdNotFoundException;
import com.getir.readingisgood.common.exception.EmailAlreadyExistsException;
import com.getir.readingisgood.domain.customer.entity.CustomerEntity;
import com.getir.readingisgood.domain.customer.repository.CustomerRepository;

@Service
@Transactional
public class CustomerDataService {

	private CustomerRepository customerRepository;

	public CustomerDataService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public CustomerEntity saveCustomer(CustomerEntity entity) {
		return customerRepository.save(entity);
	}

	public Page<CustomerEntity> getCustomers(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("name").ascending());
		Page<CustomerEntity> customers = customerRepository.findAll(pageRequest);
		return customers;
	}

	public CustomerEntity getCustomerById(Long customerId) {
		return customerRepository.findById(customerId).orElseThrow(CustomerIdNotFoundException::new);
	}

	public void checkEmailAlreadyExists(String email) {
		if (customerRepository.findByEmail(email).isPresent()) {
			throw new EmailAlreadyExistsException();
		}
	}

}
