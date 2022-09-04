package com.booksapi.service;

import com.booksapi.model.dto.BookDto;

import java.util.List;


public interface BooksService {
    BookDto createBook(BookDto bookDto,int authorId);
    List<BookDto> fetchBooks();
    BookDto getBook(int bookId) throws Exception;
    BookDto updateBook(BookDto updatedBook,int bookId) throws Exception;
    void deleteBook(int bookId) throws Exception;
    List<BookDto> getByTitle(String title) throws Exception;

}
