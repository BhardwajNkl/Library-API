package com.bhardwaj.library.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
public class AuthorRepositoryTest {
	@Autowired
    private AuthorRepository authorRepository;

	// TESTING ONLY THOSE METHODS THAT HAVE BEEN USED IN THE AUTHOR SERVICE
	
    @Test
    public void testFindAll() {
    	Author entity = new Author();
    	entity.setAuthorName("John Mcafee");
        authorRepository.save(entity);
        
        List<Author> authorList = authorRepository.findAll();
        assertEquals(1, authorList.size());
        assertThat(authorList.get(0).getAuthorName()).isEqualTo("John Mcafee");
    }
    
    @Test
    public void testFindById() {
    	Author entity = new Author();
    	entity.setAuthorName("John Mcafee");
        authorRepository.save(entity);
        
//    	int id = 2;
        Author author = authorRepository.findById(entity.getAuthorId()).orElse(null);
        assertNotNull(author);
        assertThat(author.getAuthorName()).isEqualTo("John Mcafee");
    }
    
    // TEST SAVE
}
