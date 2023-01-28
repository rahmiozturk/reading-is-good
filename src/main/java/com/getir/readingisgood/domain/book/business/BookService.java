package com.getir.readingisgood.domain.book.business;

import com.getir.readingisgood.common.model.ApiPagingResponse;
import com.getir.readingisgood.domain.book.model.BookDto;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;
import com.getir.readingisgood.domain.book.model.request.UpdateBookStockRequest;

public interface BookService {

	BookDto createBook(CreateBookRequest createReq);

	BookDto updateBookStock(UpdateBookStockRequest updateReq);

	BookDto getBookById(Long id);

	ApiPagingResponse<BookDto> getBooks(int page, int size);

}
