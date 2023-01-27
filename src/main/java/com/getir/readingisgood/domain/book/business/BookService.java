package com.getir.readingisgood.domain.book.business;

import java.util.List;

import com.getir.readingisgood.domain.book.model.BookDto;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;
import com.getir.readingisgood.domain.book.model.request.UpdateBookStockRequest;

public interface BookService {

	BookDto createBook(CreateBookRequest createReq);

	BookDto updateBookStock(UpdateBookStockRequest updateReq);

	BookDto getBookById(Long id);

	List<BookDto> getBooks(int page, int size);

}
