package com.getir.readingisgood.book;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.getir.readingisgood.domain.book.model.BookDto;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void addBookNegative() throws Exception {
		CreateBookRequest req = CreateBookRequest.builder().bookName("bookName1").price(BigDecimal.valueOf(61))
				.authorName("author1").amount(10).build();
		ResponseEntity<BookDto> response = restTemplate.postForEntity("/book/create", req,
				BookDto.class);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

}