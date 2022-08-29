package com.booksapi.model.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(name="title")
    private String title;

    private Date publishedDate;
    @Column(name = "book_price")

    private Float price;

    @Column(name = "category_id")
    private Integer catId;

    @Column(columnDefinition="TEXT")
    private String  bookDes;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name="book_poster")
    private String book_image;
}
