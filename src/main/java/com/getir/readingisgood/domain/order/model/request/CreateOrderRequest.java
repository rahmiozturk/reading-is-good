package com.getir.readingisgood.domain.order.model.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.getir.readingisgood.domain.order.model.OrderBookDto;

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
public class CreateOrderRequest {

	@NotNull
	private Long customerId;

	@Valid
	@Size(min = 1)
	private List<OrderBookDto> bookList;
}
