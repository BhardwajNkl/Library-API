package com.bhardwaj.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhardwaj.library.entity.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
