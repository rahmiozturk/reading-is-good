package com.getir.readingisgood.domain.customer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

	private Long id;
	private String name;
	private String surname;
	private String phoneNumber;

	private String email;

}
