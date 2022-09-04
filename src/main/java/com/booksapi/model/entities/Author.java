package com.booksapi.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "author")
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    private String authorName;
    @Column(name = "author_emailId")
    private String emailId;
    @Column(name = "author_bio")
    private String bio;
    @Column(name = "author_image")
    private String image;


    @JsonIgnore
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Book> bookList;

}
