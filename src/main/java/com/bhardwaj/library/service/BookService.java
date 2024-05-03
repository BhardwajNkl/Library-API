package com.bhardwaj.library.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bhardwaj.library.entity.Book;
@Service
public interface BookService extends JpaRepository<Book, Integer> {
	public Book findByBookCode(String code);
}
