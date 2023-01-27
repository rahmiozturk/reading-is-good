package com.getir.readingisgood.domain.order.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.getir.readingisgood.common.BaseEntity;
import com.getir.readingisgood.domain.customer.entity.CustomerEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuppressWarnings("serial")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Orders")
public class OrderEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	private CustomerEntity customer;

	private BigDecimal totalPrice;

	private Date orderTime;

	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	private List<OrderDetailEntity> orderDetails;

	private Integer totalAmount;

	 @Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	public static enum OrderStatus {
		ORDERED, DELIVERED;
	}

}
