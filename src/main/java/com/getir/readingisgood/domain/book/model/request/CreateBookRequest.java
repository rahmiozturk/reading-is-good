package com.getir.readingisgood.domain.book.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class CreateBookRequest {
	@NotNull
	private String bookName;
	private String authorName;
	@Min(1)
	@Max(100)
	private Integer amount;
	@Min(0)
	private BigDecimal price;
}
