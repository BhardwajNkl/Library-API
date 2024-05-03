package com.bhardwaj.library.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private String bookCode;
	private String bookName;
	private String dateAdded;
	@ManyToOne
	private Author author;
	public Book() {
		super();
	}
	
	public Book(int bookId, String bookCode, String bookName, String dateAdded, Author author) {
		super();
		this.bookId = bookId;
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.dateAdded = dateAdded;
		this.author = author;
	}

	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Author getAuthor() {
		return author;
	}
	
	public String getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	
}
