package com.bhardwaj.library.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RequestedBookModelTest {
	
    private RequestedBookModel book;

    @BeforeEach
    public void setUp() {
        book = new RequestedBookModel();
    }

    @Test
    public void testNoArgumentsConstructor() {
    	assertNotNull(book);
        assertEquals(null, book.getBookCode());
        assertEquals(null, book.getBookName());
        assertEquals(null, book.getAuthor());
        assertEquals(null, book.getDateAdded());
    }

    @Test
    public void testAllArgumentsConstructor() {
        RequestedBookModel book = new RequestedBookModel("code1", "book1", "1", "Monday, June 10, 2022");
        assertNotNull(book);
        assertEquals("code1", book.getBookCode());
        assertEquals("book1", book.getBookName());
        assertEquals("1", book.getAuthor());
        assertEquals("Monday, June 10, 2022", book.getDateAdded());
    }

    @Test
    public void testGettersAndSetters() {
        book.setBookCode("code1");
        book.setBookName("book1");
        book.setAuthor("1");
        book.setDateAdded("Monday, June 10, 2022");

        assertEquals("code1", book.getBookCode());
        assertEquals("book1", book.getBookName());
        assertEquals("1", book.getAuthor());
        assertEquals("Monday, June 10, 2022", book.getDateAdded());
    }

    @Test
    public void testToString() {
        book = new RequestedBookModel("code1", "book1", "1", "Monday, June 10, 2022");
        String expected = "PostRequestModel [bookCode=code1, bookName=book1, author=1]";
        assertEquals(expected, book.toString());
    }
}
