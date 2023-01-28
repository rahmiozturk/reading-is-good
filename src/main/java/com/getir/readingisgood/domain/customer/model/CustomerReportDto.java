package com.getir.readingisgood.domain.customer.model;

import java.math.BigDecimal;

public interface CustomerReportDto {

	String getMonth();

	Long getTotalOrderCount();

	Long getTotalBookCount();

	BigDecimal getTotalPrice();

}
