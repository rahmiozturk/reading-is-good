package com.getir.readingisgood.domain.book.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.getir.readingisgood.domain.book.model.BookDto;
import com.getir.readingisgood.domain.book.model.request.CreateBookRequest;
import com.getir.readingisgood.domain.book.model.request.UpdateBookStockRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Book API")

public interface BookApi {

	@ApiOperation(value = "", notes = "Create Book", nickname = "createBook", tags = { "Book_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Create Book Response") })
	public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookRequest request);

	@ApiOperation(value = "", notes = "Update Book Stock", nickname = "updateBookStock", tags = { "Book_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Update Book Stock Response") })
	public ResponseEntity<BookDto> updateBookStock(@Valid @RequestBody UpdateBookStockRequest request);

	@ApiOperation(value = "", notes = "Get Book By Id", nickname = "getBookById", tags = {
			"Book_API" }, consumes = "application/json, application/xml", produces = "application/json, application/xml")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Book Response") })
	public ResponseEntity<BookDto> getBookById(Long bookId);

	@ApiOperation(value = "", notes = "Get Book Pageable", nickname = "trackingTheStockOfBbooks", tags = { "Book_API" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Books  Response") })
	public ResponseEntity<List<BookDto>> getBooks(@RequestParam(value = "PAGE", defaultValue = "0") int page,
			@RequestParam(value = "SIZE", defaultValue = "10") int size);

}
