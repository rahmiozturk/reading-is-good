package com.getir.readingisgood.domain.book.business.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.getir.readingisgood.common.model.ApiPagingResponse;
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
	public ApiPagingResponse<BookDto> getBooks(int page, int size) {
		Page<BookEntity> books = bookDataService.getBooks(page, size);

		ApiPagingResponse<BookDto> pagingResponse = new ApiPagingResponse<>();
		pagingResponse.setCurrentPage(books.getNumber());
		pagingResponse.setTotalItemCount(books.getTotalElements());
		pagingResponse.setTotalPageCount(books.getTotalPages());
		pagingResponse.setItems(
				books.stream().map(entity -> bookConverter.convertToDto(entity)).collect(Collectors.toList()));
		return pagingResponse;
	}

}
