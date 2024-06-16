package com.bhardwaj.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhardwaj.library.entity.Author;
import com.bhardwaj.library.repository.AuthorRepository;

@Service
public class AuthorService{
	@Autowired
	private AuthorRepository authorRepository;
	
	public List<Author> getAuthors(){
		return this.authorRepository.findAll();
	}
}
