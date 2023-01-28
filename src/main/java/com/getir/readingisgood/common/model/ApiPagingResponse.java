package com.getir.readingisgood.common.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiPagingResponse<T> {

	private Integer currentPage;
	private Long totalItemCount;
	private Integer totalPageCount;
	private List<T> items;

}
