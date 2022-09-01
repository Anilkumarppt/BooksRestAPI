package com.booksapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.booksapi.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Optional<User> findByEmailId(String emailId);
}