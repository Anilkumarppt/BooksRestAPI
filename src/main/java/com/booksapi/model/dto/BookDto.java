package com.booksapi.model.dto;

import com.booksapi.model.entities.Author;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BookDto {
    private int bookId;
    private String title;
    private Date publishedDate;
    private Float price;
    private int genreId;
    private String bookDes;
    @JsonIgnore
    private int authorid;
    private Author author;
    private String book_image;
    private Float rating;

}
