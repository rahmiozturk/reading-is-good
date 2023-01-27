package com.getir.readingisgood.domain.customer.converter;

import org.springframework.stereotype.Component;

import com.getir.readingisgood.domain.customer.entity.CustomerEntity;
import com.getir.readingisgood.domain.customer.model.CustomerDto;
import com.getir.readingisgood.domain.customer.model.request.CreateCustomerRequest;

@Component
public class CustomerConverter {
	public CustomerDto convertToDto(CustomerEntity entity) {
		return CustomerDto.builder().id(entity.getId()).build();
	}

	public CustomerEntity convertToEntity(CustomerDto dto) {
		return CustomerEntity.builder().id(dto.getId()).build();
	}

	public CustomerEntity convertToEntity(CreateCustomerRequest req) {
		CustomerEntity entity = new CustomerEntity();
		entity.setEmail(req.getEmail());
		entity.setName(req.getName());
		entity.setSurname(req.getSurname());
		entity.setPhoneNumber(req.getPhoneNumber());
		return entity;
//		return CustomerEntity.builder().name(req.getName()).surname(req.getSurname()).email(req.getEmail())
//				.phoneNumber(req.getPhoneNumber()).build();
	}
}
