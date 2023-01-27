package com.getir.readingisgood.domain.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getir.readingisgood.domain.book.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long>{

}
