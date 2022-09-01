package com.booksapi.model.entities;

import com.booksapi.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
    private User user;

    @OneToOne
    @JoinColumn(name = "book_book_id")
    private Book book;
}
