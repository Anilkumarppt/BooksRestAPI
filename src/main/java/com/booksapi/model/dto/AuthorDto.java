package com.booksapi.model.dto;



import lombok.Data;
import java.util.List;

@Data
public class AuthorDto {
    private int authorId;
    private String authorName;
    private String emailId;
    private String bio;
    private String image;
    //private List<Integer> bookList;
    private List<BookDto> bookList;

}
