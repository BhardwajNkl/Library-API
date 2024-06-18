package com.bhardwaj.library.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bhardwaj.library.entity.Author;
import com.bhardwaj.library.entity.Book;
import com.bhardwaj.library.model.RequestedBookModel;
import com.bhardwaj.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(BookController.class)
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    Author author;
    Book book1;
    Book book2;
    private List<Book> books;

    @BeforeEach
    public void setUp() {
        author = new Author(1, "author1");
        book1 = new Book(1, "code1", "book1", "Monday, June 10, 2022", author);
        book2 = new Book(2, "code2", "book2", "Monday, June 10, 2022", author);
        books = Arrays.asList(book1, book2);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookName", is("book1")))
                .andExpect(jsonPath("$[1].bookName", is("book2")));
    }

    @Test
    public void testGetBook() throws Exception {
        when(bookService.getBookById("1")).thenReturn(books.get(0));

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookName", is("book1")));
    }

    @Test
    public void testAddBook() throws Exception {
        RequestedBookModel requestedBookModel = new RequestedBookModel("code1", "book1", "Monday, June 10, 2022", "1");
        
        when(bookService.addBook(any(RequestedBookModel.class))).thenReturn(book1);

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestedBookModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookName", is("book1")));
    }

    @Test
    public void testUpdateBook() throws Exception {
//        Author author = new Author(1, "Author One");
        RequestedBookModel requestedBookModel = new RequestedBookModel("code1", "updated book1", "Monday, June 10, 2022", "1");
        Book updatedBook = new Book(1, "Code1", "updated book1", "Monday, June 10, 2022", author);
        
        when(bookService.updateBook(any(RequestedBookModel.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestedBookModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookName", is("updated book1")));
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isOk());
    }

    // helper method for this test class
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
