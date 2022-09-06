package com.booksapi.repository;

import com.booksapi.model.entities.BooksUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BooksUser, Integer> {
    BooksUser findByUsername(String username);

    Optional<BooksUser> findByEmailId(String emailId);
}