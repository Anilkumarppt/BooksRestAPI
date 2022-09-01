package com.booksapi.model.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name="title")
    private String title;

    private Date publishedDate;
    @Column(name = "book_price")

    private Float price;

    @Column(name = "genre_id")
    private int genreId;

    @Column(columnDefinition="TEXT")
    private String  bookDes;

    @Column(name = "author_id")
    private int authorId;

    @Column(name="book_poster")
    private String book_image;

    private float rating;




}
