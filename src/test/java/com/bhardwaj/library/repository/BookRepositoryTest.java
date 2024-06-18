package com.bhardwaj.library.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.bhardwaj.library.entity.Author;
import com.bhardwaj.library.entity.Book;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
public class BookRepositoryTest {
	@Autowired
    private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	// TESTING ONLY THOSE METHODS THAT HAVE BEEN USED IN SOME SERVICE
	
    @Test
    public void testFindAll() {
    	Author authorEntity = new Author();
    	authorEntity.setAuthorName("author1");
		authorRepository.save(authorEntity);
		Book bookEntity = new Book();
		bookEntity.setBookCode("code1");
		bookEntity.setBookName("book1");
		bookEntity.setDateAdded("Monday, June 10, 2022");
		bookEntity.setAuthor(authorEntity);
        bookRepository.save(bookEntity);
        
        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList.size()).isGreaterThan(0);
        assertThat(bookList.get(0).getBookCode()).isEqualTo("code1");
        assertNotNull(bookList.get(0).getAuthor());
    }
    
    @Test
    public void testFindById() {
    	Author authorEntity = new Author();
    	authorEntity.setAuthorName("author1");
		authorRepository.save(authorEntity);
		Book bookEntity = new Book();
		bookEntity.setBookCode("code1");
		bookEntity.setBookName("book1");
		bookEntity.setDateAdded("Monday, June 10, 2022");
		bookEntity.setAuthor(authorEntity);
        bookRepository.save(bookEntity);
        
    	int id = bookEntity.getBookId();
        Book bookExpected = bookRepository.findById(id).orElse(null);
        assertNotNull(bookExpected);
        assertThat(bookExpected.getBookCode()).isEqualTo("code1");
    }
    
    @Test
    public void testFindByBookCode() {
    	
    	Author authorEntity = new Author();
    	authorEntity.setAuthorName("author1");
		authorRepository.save(authorEntity);
		Book bookEntity = new Book();
		bookEntity.setBookCode("code1");
		bookEntity.setBookName("book1");
		bookEntity.setDateAdded("Monday, June 10, 2022");
		bookEntity.setAuthor(authorEntity);
        bookRepository.save(bookEntity);
    	
    	String bookCode = "code1";
        Book bookExpected = bookRepository.findByBookCode(bookCode);
        assertNotNull(bookExpected);
        assertThat(bookExpected.getBookCode()).isEqualTo("code1");
        assertThat(bookExpected.getBookName()).isEqualTo("book1");
    }

    
    @Test
    public void testDeleteById() {
    	Author authorEntity = new Author();
    	authorEntity.setAuthorName("author1");
		authorRepository.save(authorEntity);
		Book bookEntity = new Book();
		bookEntity.setBookCode("code1");
		bookEntity.setBookName("book1");
		bookEntity.setDateAdded("Monday, June 10, 2022");
		bookEntity.setAuthor(authorEntity);
        bookRepository.save(bookEntity);
        
    	int id = bookEntity.getBookId();
        bookRepository.deleteById(id);
        Book book = bookRepository.findById(id).orElse(null);
        assertNull(book);
    }
    
}
