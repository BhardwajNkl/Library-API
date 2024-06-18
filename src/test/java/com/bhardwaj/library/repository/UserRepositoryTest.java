package com.bhardwaj.library.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.bhardwaj.library.entity.User;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE )
public class UserRepositoryTest {
	@Autowired
    private UserRepository userRepository;

	// TESTING ONLY THOSE METHODS THAT HAVE BEEN USED IN SOME SERVICE
    @Test
    public void testExistsByUsernameAndPassword() {
    	User user = new User("root","root");
		userRepository.save(user);
    	Boolean result = userRepository.existsByUsernameAndPassword("root", "root");
    	assertTrue(result);
    }
	
}