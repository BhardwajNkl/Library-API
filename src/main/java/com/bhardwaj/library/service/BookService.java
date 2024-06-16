package com.bhardwaj.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhardwaj.library.entity.Author;
import com.bhardwaj.library.entity.Book;
import com.bhardwaj.library.model.RequestedBookModel;
import com.bhardwaj.library.repository.AuthorRepository;
import com.bhardwaj.library.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	
	public List<Book> getAllBooks() {
		return this.bookRepository.findAll();
	}
	
	public Book getBookById(String id) {
		return this.bookRepository.findById(Integer.parseInt(id)).orElse(null);
	}
	
	public Book addBook(RequestedBookModel requestedBookModel) {
		// get author
		int authorId = Integer.parseInt(requestedBookModel.getAuthor());
		Author author = this.authorRepository.findById(authorId).orElse(null);
		//ensure that the book with same code does not exist
		Book testBook = this.bookRepository.findByBookCode(requestedBookModel.getBookCode());
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
		this.bookRepository.save(book);
		return book;
	}

	public Book updateBook(RequestedBookModel requestedBookModel) {
		// get author
		int authorId = Integer.parseInt(requestedBookModel.getAuthor());
		Author author = this.authorRepository.findById(authorId).orElse(null);
		// get the existing book
		Book book = this.bookRepository.findByBookCode(requestedBookModel.getBookCode());
		book.setBookName(requestedBookModel.getBookName());
		book.setAuthor(author);

		// save this
		return this.bookRepository.save(book);
	}
	
	public Book deleteBookById(String id) {
		this.bookRepository.deleteById(Integer.parseInt(id));
		return null;
	}
}
