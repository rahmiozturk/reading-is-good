package com.getir.readingisgood.domain.book.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.getir.readingisgood.common.SoftwareComponent;
import com.getir.readingisgood.domain.book.api.BookApi;
import com.getir.readingisgood.domain.book.business.BookService;
import com.getir.readingisgood.domain.book.model.BookDto;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;
import com.getir.readingisgood.domain.book.model.request.UpdateBookStockRequest;

@RestController
@RequestMapping(value = "/book", consumes = { APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE }, produces = {
		APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE })
@SoftwareComponent(name = "BookController", description = "Exposes end points to book process.", technologies = {
		"java", "rest", "https" })
public class BookController implements BookApi {

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@Override
	@PostMapping("/create")
	public ResponseEntity<BookDto> createBook(@Valid CreateBookRequest request) {
		return new ResponseEntity<>(bookService.createBook(request), HttpStatus.CREATED);
	}

	@Override
	@PutMapping("/update")
	public ResponseEntity<BookDto> updateBookStock(@Valid UpdateBookStockRequest request) {
		return new ResponseEntity<>(bookService.updateBookStock(request), HttpStatus.OK);

	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long bookId) {
		return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
	}

	@Override
	@GetMapping("/all")
	public ResponseEntity<List<BookDto>> getBooks(@RequestParam(value = "PAGE", defaultValue = "0") int page,
			@RequestParam(value = "SIZE", defaultValue = "10") int size) {
		return new ResponseEntity<>(bookService.getBooks(page, size), HttpStatus.OK);
	}

}
