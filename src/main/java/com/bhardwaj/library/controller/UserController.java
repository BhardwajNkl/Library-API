package com.bhardwaj.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bhardwaj.library.model.UserCredentialsModel;
import com.bhardwaj.library.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/verify-login-credentials")
	public boolean verifyLoginCredentials(@RequestBody UserCredentialsModel credentials) {
		return this.userService.verifyLoginCredentials(credentials);
	}
}
