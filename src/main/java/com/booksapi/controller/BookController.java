package com.booksapi.controller;

import com.booksapi.model.dto.AuthorDto;
import com.booksapi.model.dto.BookDto;
import com.booksapi.model.entities.Book;
import com.booksapi.model.entities.FilesSystemData;
import com.booksapi.payload.APIResponse;
import com.booksapi.service.BooksService;
import com.booksapi.service.FilesDBService;
import com.booksapi.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class BookController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private FilesDBService filesDBService;

    @Value("${profile.image}")
    private  String local_path;


    @PostMapping("/book")
    public ResponseEntity<?> addNewBook(@RequestBody BookDto bookDto){
        BookDto book = this.booksService.createBook(bookDto,bookDto.getAuthorId());
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    @GetMapping("/all-books")
    public ResponseEntity<List<BookDto>> fetchAllBooks(){
        List<BookDto> bookDtoList=booksService.fetchBooks();
        List<BookDto> collect = bookDtoList.stream().map(bookDto -> {
            bookDto.setAuthorId(bookDto.getAuthor().getAuthorId());
            return bookDto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(collect,HttpStatus.OK);
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
    @PutMapping("/update_book/{bookId}")
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


    @PostMapping("book/{book_id}/profile")
    public ResponseEntity<?> uploadFileToFileSystem(@PathVariable(name = "book_id") int bookId,
                                                    @RequestParam("profile") MultipartFile file)
            throws IOException {

        try {
            BookDto book = this.booksService.getBook(bookId);


            FilesSystemData filesSystemData = filesDBService.uploadFileToFileSystem(file, local_path+"/author");

            System.out.println(filesSystemData.getFilePath());

            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/"+local_path+"/")
                    .path(String.valueOf(filesSystemData.getFilePath()))
                    .toUriString();

            System.out.println("Book Image "+fileDownloadUri);
            book.setBook_image(fileDownloadUri);

            booksService.updateBook(book,bookId);

            APIResponse apiResponse = new APIResponse("Uploaded Successfully", HttpStatus.OK, true);
            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
