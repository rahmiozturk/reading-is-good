package com.getir.readingisgood.domain.book.converter;

import org.springframework.stereotype.Component;

import com.getir.readingisgood.domain.book.entity.BookEntity;
import com.getir.readingisgood.domain.book.model.BookDto;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;



@Component
public class BookConverter {
	 public BookDto convertToDto(BookEntity entity) {
	        return BookDto.builder()
	                .id(entity.getId())
	                .bookName(entity.getBookName())
	                .authorName(entity.getAuthorName())
	                .amount(entity.getAmount())
	                .price(entity.getPrice())
	                .build();
	    }


	    public BookEntity convertToEntity(BookDto dto) {
	        return BookEntity.builder()
	        		.id(dto.getId())
	                .bookName(dto.getBookName())
	                .authorName(dto.getAuthorName())
	                .amount(dto.getAmount())
	                .price(dto.getPrice())
	                .build();
	    }
	    
	    public BookEntity convertToEntity(CreateBookRequest createReq) {
	        return BookEntity.builder()
	                .bookName(createReq.getBookName())
	                .authorName(createReq.getAuthorName())
	                .amount(createReq.getAmount())
	                .price(createReq.getPrice())
	                .build();
	    }
}
