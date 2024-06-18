package com.bhardwaj.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bhardwaj.library.entity.Author;
import com.bhardwaj.library.repository.AuthorRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
	@Mock
	private AuthorRepository authorRepository;

	@InjectMocks
	private AuthorService authorService;

	private List<Author> authors;

	@BeforeEach
	public void setUp() {
		Author author1 = new Author(1, "author1");
		Author author2 = new Author(2, "author2");
		authors = Arrays.asList(author1, author2);
	}

	@Test
    public void testGetAuthors() {
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAuthors();
        assertEquals(2, result.size());
        assertEquals("author1", result.get(0).getAuthorName());
        assertEquals("author2", result.get(1).getAuthorName());
    }
}
