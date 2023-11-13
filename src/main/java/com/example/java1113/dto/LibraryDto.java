package com.example.java1113.dto;

import com.example.java1113.entity.Author;
import com.example.java1113.entity.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryDto {

    //from Book entity
    private Long id;
    private String title;
    private String publisher;
    private Author author;
    private Long authorId;

    //from Author entity
    private String authorName;

    public LibraryDto(){}
    public LibraryDto(Book book, Author author){
        this.id = book.getId();
        this.title = book.getTitle();
        this.publisher = book.getPublisher();
        this.author = author;
        this.authorName = author.getName();
        this.authorId = author.getId();
    }
}
