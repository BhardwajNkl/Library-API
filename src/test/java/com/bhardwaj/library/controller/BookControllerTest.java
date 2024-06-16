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

    private List<Book> books;

    @BeforeEach
    public void setUp() {
        Author author = new Author(1, "Author One");
        Book book1 = new Book(1, "Code1", "Book One", "2023-01-01", author);
        Book book2 = new Book(2, "Code2", "Book Two", "2023-01-02", author);
        books = Arrays.asList(book1, book2);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(books);

        mockMvc.perform(get("/book"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].bookName", is("Book One")))
                .andExpect(jsonPath("$[1].bookName", is("Book Two")));
    }

    @Test
    public void testGetBook() throws Exception {
        when(bookService.getBookById("1")).thenReturn(books.get(0));

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookName", is("Book One")));
    }

    @Test
    public void testAddBook() throws Exception {
        Author author = new Author(1, "Author One");
        RequestedBookModel requestedBookModel = new RequestedBookModel("Code3", "Book Three", "2023-01-03", "1");
        Book newBook = new Book(3, "Code3", "Book Three", "2023-01-03", author);
        
        when(bookService.addBook(any(RequestedBookModel.class))).thenReturn(newBook);

        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestedBookModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookName", is("Book Three")));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Author author = new Author(1, "Author One");
        RequestedBookModel requestedBookModel = new RequestedBookModel("Code1", "Updated Book One", "2023-01-01", "1");
        Book updatedBook = new Book(1, "Code1", "Updated Book One", "2023-01-01", author);
        
        when(bookService.updateBook(any(RequestedBookModel.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestedBookModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookName", is("Updated Book One")));
    }

    @Test
    public void testDeleteBook() throws Exception {
    	// CHECK THIS
        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isOk());
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
