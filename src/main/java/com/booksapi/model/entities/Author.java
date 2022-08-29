package com.booksapi.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
@Table(name = "author")
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;
    private String authorName;
    @Column(name = "author_emailId")
    private String emailId;
    @Column(name = "author_bio")
    private String bioGraphy;
    @Column(name = "author_image")
    private String image;

    @JsonIgnore
    @OneToMany()
    private List<Book> bookIds;

}
