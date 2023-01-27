package com.getir.readingisgood.domain.book.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.getir.readingisgood.common.exception.BookIdNotFoundException;
import com.getir.readingisgood.domain.book.entity.BookEntity;
import com.getir.readingisgood.domain.book.repository.BookRepository;

@Service
@Transactional
public class BookDataService {

	private BookRepository bookRepository;

	public BookDataService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public BookEntity saveBook(BookEntity entity) {
		return bookRepository.save(entity);
	}

	public BookEntity getBookById(Long bookId) {
		return bookRepository.findById(bookId).orElseThrow(BookIdNotFoundException::new);
	}

	public List<BookEntity> getBooks(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by("bookName").ascending());
		Page<BookEntity> customers = bookRepository.findAll(pageRequest);
		return customers.getContent();
	}

}
