package com.booksapi.repository;

import com.booksapi.model.dto.BookDto;
import com.booksapi.model.entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book,Integer> {
    //Optional<Book> findById(int bookId);
    //Optional<Book> findByTitle(String title);

    //@Query(value = "Select * from books where title LIKE BINARY CONCAT('%',:title,'%')",nativeQuery = true)
    List<Book> findByTitleContaining(String title);



}
