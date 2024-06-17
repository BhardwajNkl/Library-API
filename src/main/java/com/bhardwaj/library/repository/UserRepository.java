package com.bhardwaj.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhardwaj.library.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	boolean existsByUsernameAndPassword(String username, String password);
}
