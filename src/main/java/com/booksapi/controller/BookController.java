package com.booksapi.controller;

import com.booksapi.model.dto.BookDto;
import com.booksapi.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BookController {

    @Autowired
    private BooksService booksService;

    @PostMapping("/book")
    public ResponseEntity<?> addNewBook(@RequestBody BookDto bookDto){
        BookDto book = this.booksService.createBook(bookDto);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    @GetMapping("/all-books")
    public ResponseEntity<List<BookDto>> fetchAllBooks(){
            return new ResponseEntity<>(booksService.fetchBooks(),HttpStatus.OK);
    }
}
