package com.booksapi.model.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "title")
    private String title;

    private Date publishedDate;
    @Column(name = "book_price")

    private Float price;

    @Column(name = "genre_id")
    private int genreId;

    @Column(columnDefinition = "TEXT", length = 100000)
    private String bookDes;

    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "authorId")
    private Author author;

    @Column(name = "book_poster")
    private String book_image;

    private float rating;

}
