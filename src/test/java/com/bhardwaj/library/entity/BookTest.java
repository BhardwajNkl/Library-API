package com.bhardwaj.library.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookTest {

	private Book book;
    private Author mockAuthor;

    @BeforeEach
    public void setUp() {
        mockAuthor = mock(Author.class);
        book = new Book();
    }

    @Test
    public void testNoArgumentsConstructor() {
        assertNotNull(book);
    }

    @Test
    public void testAllArgumentsConstructor() {
        Book book = new Book(1, "code1", "book1", "Monday, June 10, 2022", mockAuthor);
        assertEquals(1, book.getBookId());
        assertEquals("code1", book.getBookCode());
        assertEquals("book1", book.getBookName());
        assertEquals("Monday, June 10, 2022", book.getDateAdded());
        assertEquals(mockAuthor, book.getAuthor());
    }

    @Test
    public void testGettersAndSetters() {
        book.setBookId(1);
        book.setBookCode("code1");
        book.setBookName("book1");
        book.setDateAdded("Monday, June 10, 2022");
        book.setAuthor(mockAuthor);

        assertEquals(1, book.getBookId());
        assertEquals("code1", book.getBookCode());
        assertEquals("book1", book.getBookName());
        assertEquals("Monday, June 10, 2022", book.getDateAdded());
        assertEquals(mockAuthor, book.getAuthor());
    }
}
