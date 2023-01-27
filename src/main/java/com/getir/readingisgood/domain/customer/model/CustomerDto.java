package com.getir.readingisgood.domain.customer.model;

import java.util.List;

import com.getir.readingisgood.domain.book.model.BookDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {

	private Long id;
	private List<BookDto> bookList;
	private Integer amount;

}
