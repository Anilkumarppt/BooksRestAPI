package com.booksapi.service;

import com.booksapi.exception.ResourceNotFoundEx;
import com.booksapi.model.dto.BookDto;

import java.util.List;


public interface BooksService {
    BookDto createBook(BookDto bookDto, int authorId);

    List<BookDto> fetchBooks();

    BookDto getBook(int bookId) throws ResourceNotFoundEx;

    BookDto updateBook(BookDto updatedBook, int bookId) throws ResourceNotFoundEx;

    void deleteBook(int bookId) throws ResourceNotFoundEx;

    List<BookDto> getByTitle(String title) throws ResourceNotFoundEx;

}
