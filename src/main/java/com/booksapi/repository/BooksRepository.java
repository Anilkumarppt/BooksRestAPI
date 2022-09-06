package com.booksapi.repository;

import com.booksapi.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BooksRepository extends JpaRepository<Book, Integer> {
    //Optional<Book> findById(int bookId);
    //Optional<Book> findByTitle(String title);

    //@Query(value = "Select * from books where title LIKE BINARY CONCAT('%',:title,'%')",nativeQuery = true)
    List<Book> findByTitleContaining(String title);


    List<Book> findByAuthorAuthorId(int authorId);


}
