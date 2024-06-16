package com.bhardwaj.library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RequestedBookModelTest {
	
	private RequestedBookModel demoBook;
	
	@BeforeEach
	void setUp() {
		demoBook = new RequestedBookModel();
	}
	
	@Test
	void testGettersAndSetters(){		
		demoBook.setAuthor("1");
		demoBook.setBookCode("code101");
		demoBook.setBookName("new book");
		demoBook.setDateAdded("Monday, June 10, 2022");
		
		assertEquals("1", demoBook.getAuthor());
		assertEquals("code101", demoBook.getBookCode());
		assertEquals("new book", demoBook.getBookName());
		assertEquals("Monday, June 10, 2022", demoBook.getDateAdded());
	}
	
	@Test
	void testToString() {
		demoBook.setAuthor("1");
		demoBook.setBookCode("code101");
		demoBook.setBookName("new book");
		demoBook.setDateAdded("Monday, June 10, 2022");
		
		assertEquals("PostRequestModel [bookCode=code101, bookName=new book, author=1]", demoBook.toString());
	}
}
