package com.bhardwaj.library.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.bhardwaj.library.entity.Author;
@Service
public interface AuthorService extends JpaRepository<Author, Integer>{

}
