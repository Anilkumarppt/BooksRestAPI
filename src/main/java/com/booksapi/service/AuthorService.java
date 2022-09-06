package com.booksapi.service;

import com.booksapi.exception.ResourceNotFoundEx;
import com.booksapi.model.dto.AuthorDto;

import java.util.List;


public interface AuthorService {

    public AuthorDto getAuthor(int id) throws ResourceNotFoundEx;

    public AuthorDto createAuthor(AuthorDto authorDto);

    public List<AuthorDto> getAllAuthors();

    public AuthorDto updateAuthor(AuthorDto authorDto, int authorID) throws ResourceNotFoundEx;

    public void deleteAuthor(int id);


}
