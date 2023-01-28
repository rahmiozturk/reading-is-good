package com.getir.readingisgood.book;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.domain.book.entity.BookEntity;
import com.getir.readingisgood.domain.book.model.BookDto;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;
import com.getir.readingisgood.domain.book.model.request.UpdateBookStockRequest;
import com.getir.readingisgood.domain.book.repository.BookRepository;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

	@MockBean
	private BookRepository bookRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void addBook() throws Exception {
		CreateBookRequest req = CreateBookRequest.builder().bookName("bookName1").price(BigDecimal.valueOf(61))
				.authorName("author1").amount(10).build();
		ResponseEntity<BookDto> response = restTemplate.postForEntity("/book/create", req, BookDto.class);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

	@Test
	@Order(2)
	public void updateBookStok() throws Exception {
		BookEntity entity = BookEntity.builder().id(1L).amount(5).authorName("author2").bookName("bookbName2").price(BigDecimal.valueOf(50)).build();
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
		
		UpdateBookStockRequest req = UpdateBookStockRequest.builder().amount(60).bookId(1L).build();
		ResponseEntity<BookDto> response = restTemplate.postForEntity("/book/update", req, BookDto.class);
		Assertions.assertTrue(response.getBody().getAmount().equals(60L));

	}

}