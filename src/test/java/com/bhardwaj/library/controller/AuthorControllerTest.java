package com.bhardwaj.library.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.MockMvc;


import com.bhardwaj.library.entity.Author;
import com.bhardwaj.library.service.AuthorService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    private List<Author> authors;

    @BeforeEach
    public void setUp() {
        Author author1 = new Author(1, "name1");
        Author author2 = new Author(2, "name2");
        authors = Arrays.asList(author1, author2);
    }

    @Test
    public void testGetAll() throws Exception {
        when(authorService.getAuthors()).thenReturn(authors);

        mockMvc.perform(get("/author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].authorName", is("name1")))
                .andExpect(jsonPath("$[1].authorName", is("name2")));
    }
}
