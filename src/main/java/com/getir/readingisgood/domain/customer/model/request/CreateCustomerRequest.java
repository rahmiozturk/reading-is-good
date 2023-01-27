package com.getir.readingisgood.domain.customer.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class CreateCustomerRequest {

	private String name;
	private String surname;

	private String phoneNumber;

	@Email
	@NotNull
	private String email;

}
