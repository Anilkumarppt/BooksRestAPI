package com.booksapi.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "reservation")
@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int res_id;

    @OneToOne
    @JoinColumn(name = "user_user_id")
    private BooksUser booksUser;

    @OneToOne
    @JoinColumn(name = "book_book_id")
    private Book book;
}
