package com.example.java1113.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name="author_one2many")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    @OneToMany
    private List<Book> books = new ArrayList<>();
}

