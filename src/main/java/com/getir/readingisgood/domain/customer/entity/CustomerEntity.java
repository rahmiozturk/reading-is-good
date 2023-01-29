package com.getir.readingisgood.domain.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.getir.readingisgood.common.model.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuppressWarnings("serial")

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Customers")
public class CustomerEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String userId;
	
	@NotNull
	private String name;
	private String surname;
	private String phoneNumber;

	@Column(unique = true)
	private String email;

	private String role = "USER";

}
