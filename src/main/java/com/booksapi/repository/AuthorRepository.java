package com.booksapi.repository;

import com.booksapi.model.dto.BookDto;
import com.booksapi.model.entities.Author;
import com.booksapi.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    //public List<Book> getAllAuthorBooks(int authorId);

}
