package com.getir.readingisgood.domain.book.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {

	private Long id;
	private String bookName;
	private String authorName;
	private Integer amount;
	private BigDecimal price;

}
