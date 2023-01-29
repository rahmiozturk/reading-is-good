package com.getir.readingisgood.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.readingisgood.domain.book.business.BookService;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;
import com.getir.readingisgood.domain.book.model.request.UpdateBookStockRequest;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private BookService bookService;

	@BeforeAll
	@Transactional
	public void insertDummyData() {

		bookService.createBook(CreateBookRequest.builder().bookName("bookName1").authorName("authorName1").amount(10)
				.price(BigDecimal.valueOf(110)).build());

		bookService.createBook(CreateBookRequest.builder().bookName("bookName2").authorName("authorName2").amount(20)
				.price(BigDecimal.valueOf(120)).build());
	}

	@Test
	@WithMockUser(username = "5fc03087-d265-11e7-b8c6-83e29cd24f4c", password = "userpass", roles = { "ADMIN" })
	public void whenCreateBookThenReturnSuccessAndExpectedBookNameIsCorrect() throws Exception {
		CreateBookRequest request = CreateBookRequest.builder().bookName("bookName1").authorName("authorName1")
				.amount(10).price(BigDecimal.valueOf(110)).build();

		mockMvc.perform(post("/book/admin/create").contentType(APPLICATION_JSON_VALUE).accept(APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.bookName").value("bookName1"));
	}

	@Test
	@WithMockUser(username = "07344d08-ec0d-11ec-8ea0-0242ac120002", password = "userpass", roles = { "USER" })
	public void whenCreateBookThenThrowsException() throws Exception {
		CreateBookRequest request = CreateBookRequest.builder().bookName("bookName1").authorName("authorName1")
				.amount(10).price(BigDecimal.valueOf(110)).build();

		mockMvc.perform(post("/book/admin/create").contentType(APPLICATION_JSON_VALUE).accept(APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "5fc03087-d265-11e7-b8c6-83e29cd24f4c", password = "userpass", roles = { "ADMIN" })
	public void whenUpdateBookStockThenReturnSuccessAndExpectedAmountIsCorrect() throws Exception {

		UpdateBookStockRequest request = UpdateBookStockRequest.builder().amount(30).bookId(1L).build();

		mockMvc.perform(put("/book/admin/update").contentType(APPLICATION_JSON_VALUE).accept(APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.amount").value(30L));
	}

	@Test
	@WithMockUser(username = "5fc03087-d265-11e7-b8c6-83e29cd24f4c", password = "userpass", roles = { "USER" })
	public void whenUpdateBookStockThenThenThrowsException() throws Exception {

		UpdateBookStockRequest request = UpdateBookStockRequest.builder().amount(30).bookId(1L).build();

		mockMvc.perform(put("/book/admin/update").contentType(APPLICATION_JSON_VALUE).accept(APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isForbidden());

	}
}
