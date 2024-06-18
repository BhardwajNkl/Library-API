package com.bhardwaj.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bhardwaj.library.entity.Author;
import com.bhardwaj.library.entity.Book;
import com.bhardwaj.library.model.RequestedBookModel;
import com.bhardwaj.library.repository.AuthorRepository;
import com.bhardwaj.library.repository.BookRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	@Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    private List<Book> books;
    private Author author;
    private Book book;

    @BeforeEach
    public void setUp() {
        author = new Author(1, "author1");
        book = new Book(1, "code1", "book1", "Monday, June 10, 2022", author);
        books = Arrays.asList(book);
    }

    @Test
    public void testGetAllBooks() {
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();
        assertEquals(1, result.size());
        assertEquals("book1", result.get(0).getBookName());
    }

    @Test
    public void testGetBookById() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById("1");
        assertEquals("book1", result.getBookName());
    }

    @Test
    public void testGetBookByIdNotFound() {
        when(bookRepository.findById(1)).thenReturn(Optional.empty());

        Book result = bookService.getBookById("1");
        assertNull(result);
    }

    @Test
    public void testAddBook() {
        RequestedBookModel requestedBookModel = new RequestedBookModel("code1", "book1", "1", "Monday, June 10, 2022");
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(bookRepository.findByBookCode("code1")).thenReturn(null);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.addBook(requestedBookModel);
        assertEquals("book1", result.getBookName());
    }

    @Test
    public void testAddBookExistingBookCode() {
        RequestedBookModel requestedBookModel = new RequestedBookModel("code1", "book1", "1", "Monday, June 10, 2022");
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(bookRepository.findByBookCode("code1")).thenReturn(book);

        Book result = bookService.addBook(requestedBookModel);
        assertNull(result);
    }

    @Test
    public void testUpdateBook() {
        RequestedBookModel requestedBookModel = new RequestedBookModel("code1", "updated book1", "1", "Monday, June 10, 2022");
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(bookRepository.findByBookCode("code1")).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.updateBook(requestedBookModel);
        assertEquals("updated book1", result.getBookName());
    }

    @Test
    public void testDeleteBookById() {
        Book result = bookService.deleteBookById("1");
        assertNull(result);
    }
}
