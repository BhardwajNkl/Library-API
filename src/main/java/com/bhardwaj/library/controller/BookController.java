package com.bhardwaj.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhardwaj.library.entity.Book;
import com.bhardwaj.library.model.RequestedBookModel;
import com.bhardwaj.library.service.BookService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;

	@GetMapping("/book")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable String id) {
		return this.bookService.getBookById(id);
	}
	@CrossOrigin("*")
	@PostMapping("/book")
	public Book addBook(@RequestBody RequestedBookModel requestedBookModel) {
		return this.bookService.addBook(requestedBookModel);
	}
	@CrossOrigin("*")
	@PutMapping("/book")
	public Book updateBook(@RequestBody RequestedBookModel requestedBookModel) {
		return this.bookService.updateBook(requestedBookModel);
	}
	@CrossOrigin("*")
	@DeleteMapping("/book/{id}")
	public Book deleteBook(@PathVariable String id) {
		return this.bookService.deleteBookById(id);
	}

}
