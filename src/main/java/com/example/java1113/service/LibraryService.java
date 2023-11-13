package com.example.java1113.service;

import com.example.java1113.dto.LibraryDto;
import com.example.java1113.entity.Author;
import com.example.java1113.entity.Book;
import com.example.java1113.repo.AuthorRepo;
import com.example.java1113.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final AuthorRepo authorRepository;
    private final BookRepo bookRepository;

    @Autowired
    public LibraryService(AuthorRepo authorRepository, BookRepo bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    
    public Author findByName(String name){
        return authorRepository.findByName(name);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    public void createBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(Long bookId, LibraryDto updatedBook) {
        Book existingBook = bookRepository.findById(bookId)
                .orElse(null); 

        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setPublisher(updatedBook.getPublisher());

            Author existingAuthor = authorRepository.findById(existingBook.getAuthor().getId()).orElse(null);
            existingAuthor.setName(updatedBook.getAuthorName());
            authorRepository.save(existingAuthor);

            return bookRepository.save(existingBook);
        } else {
            return null; 
        }
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}

