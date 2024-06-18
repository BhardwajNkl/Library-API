package com.bhardwaj.library.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthorTest {

    private Author author;

    @BeforeEach
    public void setUp() {
        author = new Author();
    }

    @Test
    public void testNoArgumentsConstructor() {
        assertNotNull(author);
    }

    @Test
    public void testAllArgumentsConstructor() {
        Author author = new Author(1, "name1");
        assertEquals(1, author.getAuthorId());
        assertEquals("name1", author.getAuthorName());
    }

    @Test
    public void testGettersAndSetters() {
        author.setAuthorId(1);
        author.setAuthorName("name1");

        assertEquals(1, author.getAuthorId());
        assertEquals("name1", author.getAuthorName());
    }

    @Test
    public void testToString() {
        author.setAuthorId(1);
        author.setAuthorName("name1");

        String expectedString = "Author [authorId=1, authorName=name1]";
        assertEquals(expectedString, author.toString());
    }
}