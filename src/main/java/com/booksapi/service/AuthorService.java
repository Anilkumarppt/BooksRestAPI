package com.booksapi.service;

import com.booksapi.model.dto.AuthorDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthorService {

    public AuthorDto getAuthor(int id);

    public AuthorDto createAuthor(AuthorDto authorDto);

    public List<AuthorDto> getAllAuthors();

    public AuthorDto updateAuthor(AuthorDto authorDto,int authorID);

    public void deleteAuthor(int id);


}
