package com.booksapi.repository;

import com.booksapi.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book,Long> {
    Optional<Book> findById(Long bookId);
}
