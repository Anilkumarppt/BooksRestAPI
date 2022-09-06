package com.booksapi.service.seviceImpl;

import com.booksapi.exception.ResourceNotFoundEx;
import com.booksapi.model.dto.AuthorDto;
import com.booksapi.model.dto.BookDto;
import com.booksapi.model.entities.Author;
import com.booksapi.model.entities.Book;
import com.booksapi.repository.AuthorRepository;
import com.booksapi.repository.BooksRepository;
import com.booksapi.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public AuthorDto getAuthor(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundEx("Resource Not Found", HttpStatus.NOT_FOUND));
      /*  List<Book> byAuthor = booksRepository.findByAuthor(author.getAuthorId());
        author.setBookList(byAuthor);
        AuthorDto authorDto=mapper.map(author,AuthorDto.class);
        List<BookDto> collect = byAuthor.stream().map(book -> mapper.map(book, BookDto.class)).collect(Collectors.toList());
        authorDto.setBookList(collect);
      */
        return updatedAuthorDto(author);
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author newAuthor = new Author();
        newAuthor.setAuthorId(authorDto.getAuthorId());
        newAuthor.setBio(authorDto.getBio());
        newAuthor.setAuthorName(authorDto.getAuthorName());
        newAuthor.setEmailId(authorDto.getEmailId());
        newAuthor.setImage(authorDto.getImage());
        newAuthor.setAuthorId(authorDto.getAuthorId());
        if (authorDto.getImage() == null || authorDto.getImage().isEmpty())
            authorDto.setImage("default.png");
        /*List<Book> byAuthor = booksRepository.findByAuthor(authorDto.getAuthorId());
        newAuthor.setBookList(byAuthor);
        //newAuthor.setBookList(authorDto.getBookList());
        Author result=authorRepository.save(newAuthor);
        List<BookDto> collect = result.getBookList().stream().map(book -> mapper.map(book, BookDto.class)).collect(Collectors.toList());
        AuthorDto authorDtoResult=mapper.map(result,AuthorDto.class);
        authorDtoResult.setBookDtoList(collect);*/
        return updatedAuthorDto(newAuthor);
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();
        List<AuthorDto> authorDtosList = authorList.stream().map(author -> {
                    author.setBookList(new ArrayList<>());
                    return this.mapper.map(author, AuthorDto.class);
                }
        ).collect(Collectors.toList());
        return authorDtosList;
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto authorDto, int authorId) {
        Author newAuthor = authorRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundEx("Resource Not Found", HttpStatus.NOT_FOUND));
        //newAuthor.setAuthorId(authorDto.getAuthorId());
        newAuthor.setBio(authorDto.getBio());
        newAuthor.setAuthorName(authorDto.getAuthorName());
        newAuthor.setEmailId(authorDto.getEmailId());
        newAuthor.setImage(authorDto.getImage());
        return updatedAuthorDto(newAuthor);
        //newAuthor.setBookList(authorDto.getBookList());
        /*Author result=authorRepository.save(newAuthor);
        return mapper.map(result,AuthorDto.class);*/
    }

    @Override
    public void deleteAuthor(int id) {
        Author newAuthor = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundEx("Resource Not Found", HttpStatus.NOT_FOUND));
        authorRepository.delete(newAuthor);
    }

    private AuthorDto updatedAuthorDto(Author newAuthor) {
        List<Book> byAuthor = booksRepository.findByAuthorAuthorId(newAuthor.getAuthorId());
        newAuthor.setBookList(byAuthor);
        Author result = authorRepository.save(newAuthor);
        List<BookDto> collect = result.getBookList().stream().map(book -> {
            return mapper.map(book, BookDto.class);
        }).collect(Collectors.toList());
        AuthorDto authorDtoResult = mapper.map(result, AuthorDto.class);
        authorDtoResult.setBookList(collect);
        return authorDtoResult;
    }
}
