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

import com.bhardwaj.library.entity.Author;
import com.bhardwaj.library.entity.Book;
import com.bhardwaj.library.model.RequestedBookModel;
import com.bhardwaj.library.service.AuthorService;
import com.bhardwaj.library.service.BookService;

@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;

	@GetMapping("/book")
	public List<Book> getAllBooks() {
		return bookService.findAll();
	}
	
	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable String id) {
		Book book = bookService.findById(Integer.parseInt(id)).orElse(null);
		return book;
	}
	@CrossOrigin("*")
	@PostMapping("/book")
	public Book addBook(@RequestBody RequestedBookModel requestedBookModel) {
		// get author
		int authorId = Integer.parseInt(requestedBookModel.getAuthor());
		Author author = authorService.findById(authorId).orElse(null);
		//ensure that the book with same code does not exist
		Book testBook = bookService.findByBookCode(requestedBookModel.getBookCode());
		if(testBook!=null) {
			return null;
		}
		// create a book
		Book book = new Book();
		book.setBookCode(requestedBookModel.getBookCode());
		book.setBookName(requestedBookModel.getBookName());
		book.setDateAdded(requestedBookModel.getDateAdded());
		book.setAuthor(author);
		// save this
		bookService.save(book);
		return book;
	}
	@CrossOrigin("*")
	@PutMapping("/book")
	public Book updateBook(@RequestBody RequestedBookModel requestedBookModel) {
		// get author
		int authorId = Integer.parseInt(requestedBookModel.getAuthor());
		Author author = authorService.findById(authorId).orElse(null);
		// get the existing book
		Book book = bookService.findByBookCode(requestedBookModel.getBookCode());
		book.setBookName(requestedBookModel.getBookName());
		book.setAuthor(author);

		// save this
		bookService.save(book);
		return book;
	}
	@CrossOrigin("*")
	@DeleteMapping("/book/{id}")
	public Book deleteBook(@PathVariable String id) {
		bookService.deleteById(Integer.parseInt(id));
		return null;
	}

}
