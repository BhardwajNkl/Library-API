package com.bhardwaj.library;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bhardwaj.library.entity.Author;
import com.bhardwaj.library.entity.User;
import com.bhardwaj.library.repository.AuthorRepository;
import com.bhardwaj.library.repository.UserRepository;

@Component
public class DBInitializer implements CommandLineRunner {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Author author1 = new Author();
		Author author2 = new Author();
		Author author3 = new Author();
		Author author4 = new Author();
		author1.setAuthorName("K R Venugopal");
		author2.setAuthorName("Denis Ritchie");
		author3.setAuthorName("Dr S Radhakrishnan");
		author4.setAuthorName("John Mcafee");
		
		authorRepository.saveAll(Arrays.asList(author1, author2, author3, author4));
		
		User user1 = new User("root","root");
		User user2 = new User("admin","admin");
		User user3 = new User("nikhil","nikhil");
		User user4 = new User("nagarro","nagarro");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));
		
	}
	
}
