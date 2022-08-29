package com.booksapi.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class BookDto {
    private Long bookId;
    private String title;
    private Date publishedDate;
    private Float price;
    private Integer catId;
    private String bookDes;
    private Integer authorId;
    private String book_image;

}
