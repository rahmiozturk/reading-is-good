package com.getir.readingisgood.domain.book.business.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.getir.readingisgood.domain.book.business.BookService;
import com.getir.readingisgood.domain.book.converter.BookConverter;
import com.getir.readingisgood.domain.book.data.BookDataService;
import com.getir.readingisgood.domain.book.entity.BookEntity;
import com.getir.readingisgood.domain.book.model.BookDto;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;
import com.getir.readingisgood.domain.book.model.request.UpdateBookStockRequest;

@Service
public class BookServiceImpl implements BookService {

	private BookDataService bookDataService;
	private BookConverter bookConverter;

	public BookServiceImpl(BookDataService bookDataService, BookConverter bookConverter) {
		this.bookDataService = bookDataService;
		this.bookConverter = bookConverter;
	}

	@Override
	public BookDto createBook(CreateBookRequest request) {

		BookEntity created = bookDataService.saveBook(bookConverter.convertToEntity(request));
		return bookConverter.convertToDto(created);
	}

	@Override
	public BookDto updateBookStock(UpdateBookStockRequest updateReq) {

		BookEntity current = bookDataService.getBookById(updateReq.getBookId());
		current.setAmount(updateReq.getAmount());
		BookEntity updated = bookDataService.saveBook(current);

		return bookConverter.convertToDto(updated);
	}

	@Override
	public BookDto getBookById(Long bookId) {

		BookEntity bookById = bookDataService.getBookById(bookId);
		return bookConverter.convertToDto(bookById);
	}

	@Override
	public List<BookDto> getBooks(int page, int size) {
		List<BookEntity> entityList = bookDataService.getBooks(page, size);
		List<BookDto> dtoList = entityList.stream().map(entity -> bookConverter.convertToDto(entity))
				.collect(Collectors.toList());
		return dtoList;
	}

}
