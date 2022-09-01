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
    @GetMapping("/book/{book_id}")
    public ResponseEntity<?> fetchSingleBook(@PathVariable("book_id") int bookId){
        BookDto bookDto;
        try {
            bookDto=booksService.getBook(bookId);
            return ResponseEntity.ok(bookDto);
        } catch (Exception e) {
            return new ResponseEntity<>("Book not found!",HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/update_book/{bookId}")
    public ResponseEntity<?> updateBookItem(@RequestBody BookDto newBook,@PathVariable int bookId){
        BookDto bookDto;
        try{
            bookDto=booksService.updateBook(newBook,bookId);
            return ResponseEntity.ok(bookDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("book/delete/{book_id}")
    public ResponseEntity<?> deleteBookItem(@PathVariable("book_id") int bookId){
        try {
            booksService.deleteBook(bookId);
            return ResponseEntity.ok("Book Deleted Successfully");
        }
        catch (Exception e){
            return new ResponseEntity<>("Book not found",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("book/title")
    public ResponseEntity<?> findByTitle(@RequestParam("title") String title) throws Exception {
        List<BookDto> bookDto=booksService.getByTitle(title);
        return ResponseEntity.ok(bookDto);
    }
}
