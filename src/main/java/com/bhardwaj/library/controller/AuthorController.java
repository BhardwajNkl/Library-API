package com.bhardwaj.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bhardwaj.library.entity.Author;
import com.bhardwaj.library.service.AuthorService;

@RestController
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/author")
	public List<Author> getAll() {
		return authorService.findAll();
	}
}
