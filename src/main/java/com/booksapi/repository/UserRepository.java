package com.booksapi.repository;

import com.booksapi.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    Optional<User> findByEmailId(String emailId);
}