package com.booksapi.repository;

import com.booksapi.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    //public List<Book> getAllAuthorBooks(int authorId);

}
