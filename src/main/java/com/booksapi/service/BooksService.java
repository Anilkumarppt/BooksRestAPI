package com.booksapi.service;

import com.booksapi.model.dto.BookDto;

import java.util.List;


public interface BooksService {
    BookDto createBook(BookDto bookDto);
    List<BookDto> fetchBooks();
    BookDto getBook(Long bookId) throws Exception;
    BookDto updateBook(BookDto updatedBook,Long bookId) throws Exception;
    void deleteBook(Long bookId) throws Exception;
    BookDto getByTitle(String title);

}
