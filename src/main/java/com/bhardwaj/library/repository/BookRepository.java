package com.bhardwaj.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bhardwaj.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	public Book findByBookCode(String code);
}
