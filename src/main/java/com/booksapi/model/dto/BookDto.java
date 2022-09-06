package com.booksapi.model.dto;

import com.booksapi.model.entities.Author;
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

    private int authorId;
    private Author author;
    private String book_image;
    private Float rating;

    public void setAuthorId(int authorId){
        if(author!=null && author.getAuthorId()!=0)
            this.authorId =author.getAuthorId();
        else
            this.authorId=authorId;
    }

}
