package com.booksapi.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "booksuser")
@Getter
@Setter
public class BooksUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String emailId;

    @OneToMany(mappedBy = "booksUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

}

