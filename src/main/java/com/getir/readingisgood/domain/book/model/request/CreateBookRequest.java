package com.getir.readingisgood.domain.book.model.request;

import java.math.BigDecimal;

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
public class CreateBookRequest {
	private String bookName;
	private String authorName;
	private Integer amount;
	private BigDecimal price;
}
