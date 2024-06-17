package com.bhardwaj.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhardwaj.library.model.UserCredentialsModel;
import com.bhardwaj.library.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public boolean verifyLoginCredentials(UserCredentialsModel credentials) {
		return this.userRepository.existsByUsernameAndPassword(credentials.getUsername(),
				credentials.getPassword());
	}

}
