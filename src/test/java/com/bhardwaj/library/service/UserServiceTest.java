package com.bhardwaj.library.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bhardwaj.library.model.UserCredentialsModel;
import com.bhardwaj.library.repository.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	UserCredentialsModel credentials;

	@BeforeEach
	public void setUp() {
		credentials = new UserCredentialsModel("root", "root");
	}

	@Test
    public void testVerifyLoginCredentialsSuccessful() {
        when(userRepository.existsByUsernameAndPassword(credentials.getUsername(),
        		credentials.getPassword())).thenReturn(true);

        Boolean result = userService.verifyLoginCredentials(credentials);
        
        assertTrue(result);
    }
	
	// useless test
	@Test
    public void testVerifyLoginCredentialsFailure() {
        when(userRepository.existsByUsernameAndPassword(credentials.getUsername(),
        		credentials.getPassword())).thenReturn(false);

        Boolean result = userService.verifyLoginCredentials(credentials);
        
        assertFalse(result);
    }
}