package com.getir.readingisgood.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.getir.readingisgood.common.exception.BookIdNotFoundException;
import com.getir.readingisgood.common.model.ApiPagingResponse;
import com.getir.readingisgood.domain.book.business.impl.BookServiceImpl;
import com.getir.readingisgood.domain.book.data.BookDataService;
import com.getir.readingisgood.domain.book.entity.BookEntity;
import com.getir.readingisgood.domain.book.model.BookDto;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;
import com.getir.readingisgood.domain.book.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BookControllerTest {

	@InjectMocks
	BookDataService bookDataService;

	@Autowired
	BookServiceImpl bookService;

	@Mock
	BookRepository bookRepository;

	private static BookDto bookDto;
	private static BookEntity bookEntity;

	@BeforeEach
	public void init() {
		bookDto = BookDto.builder().id(1L).bookName("bookName1").authorName("authorName1").amount(10)
				.price(BigDecimal.valueOf(61)).build();

		bookEntity = BookEntity.builder().id(1L).bookName("bookName1").authorName("authorName1").amount(10)
				.price(BigDecimal.valueOf(61)).build();
	}

	@Test
	@Order(1)
	public void getBookById_whenBookNotFound_thenBookIdNotFoundException() {
		when(bookRepository.findById(any())).thenReturn(Optional.empty());

		assertThrows(BookIdNotFoundException.class, () -> bookDataService.getBookById(bookEntity.getId()));
	}

	@Test
	@Order(2)
	public void saveConsumer_thenReturnSuccessResponse() {

		when(bookDataService.saveBook(bookEntity)).thenReturn(bookEntity);

		BookEntity saveBook = bookDataService.saveBook(bookEntity);
		assertNotNull(saveBook);
		assertEquals(bookDto.getBookName(), saveBook.getBookName());
	}

	@Test
	@Order(3)
	public void createBookRequest() {
		CreateBookRequest req = CreateBookRequest.builder().bookName("bookName1").authorName("authorName1").amount(10)
				.price(BigDecimal.valueOf(61)).build();

		BookDto createBook = bookService.createBook(req);
		assertEquals(createBook.getBookName(), bookEntity.getBookName());
	}

	@Test
	@Order(4)
	public void getBookAllPageAble() {
		int page = 0;
		int size = 10;
		ApiPagingResponse<BookDto> books = bookService.getBooks(page, size);
		assertEquals(books.getCurrentPage(), page);
	}

}