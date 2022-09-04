package com.booksapi.controller;

import com.booksapi.model.dto.AuthorDto;
import com.booksapi.model.entities.Author;
import com.booksapi.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/author")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDto authorDto){
        AuthorDto author = authorService.createAuthor(authorDto);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping("/author/{author_id}")
    public ResponseEntity<?> getAuthor(@PathVariable("author_id") int id){
        AuthorDto authorDto=authorService.getAuthor(id);
        return  ResponseEntity.ok(authorDto);
    }

    @PutMapping("/author/update/{author_id}")
    public ResponseEntity<?> updateAuthor(@RequestBody AuthorDto authorDto,int author_id){
        AuthorDto updatedAuthor=authorService.updateAuthor(authorDto,author_id);
        return ResponseEntity.ok(updatedAuthor);
    }
    @GetMapping("author/find-all")
    public ResponseEntity<?> getAllAuthors(){
        List<AuthorDto> allAuthors = authorService.getAllAuthors();
        return ResponseEntity.ok(allAuthors);
    }

    @DeleteMapping("author/delete/{author_id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("author_id") int id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Delete Author Successfully!",HttpStatus.OK);
    }
}
