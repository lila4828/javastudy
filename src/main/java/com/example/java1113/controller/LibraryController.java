package com.example.java1113.controller;

import com.example.java1113.dto.LibraryDto;
import com.example.java1113.entity.Author;
import com.example.java1113.entity.Book;
import com.example.java1113.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LibraryController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/book-list")
    public String showList(Model model){
        List<Author> authors = libraryService.getAllAuthors();
        List<Book> books = libraryService.getAllBooks();
        System.out.println(books.size());

        List<LibraryDto> libraryDtos = new ArrayList<>();

        for(Book b:books) {
            libraryDtos.add(new LibraryDto(b, b.getAuthor()));
        }

        model.addAttribute("libraryList", libraryDtos);


        return "view/book-list";
    }

    @GetMapping("/addBook")
    public String addBook(Model model){
        model.addAttribute("book", new LibraryDto());
        return "view/book-add";
    }

    @PostMapping("/addBook")
    public String addBookWithAuthor(@ModelAttribute LibraryDto libraryDto) {
        String title = libraryDto.getTitle();
        String publisher = libraryDto.getPublisher();
        String authorName = libraryDto.getAuthorName();

        // 존재하는 Author(name이 동일)이면 그대로 사용
        // 그렇지 않으면 Create Author
        Author author;
        Author existAuthor = libraryService.findByName(authorName);
        if(existAuthor != null){
            author = existAuthor;
        }else {
            author = new Author();
            author.setName(authorName);
            author = libraryService.createAuthor(author);
        }
        // Create Book
        Book book = new Book();
        book.setTitle(title);
        book.setPublisher(publisher);
        book.setAuthor(author);
        libraryService.createBook(book);

        return "redirect:/book-list";
    }

    @GetMapping("/editBook/{id}")
    public String showEditUserForm(@PathVariable("id") Long bookId, Model model) {
        Book book = libraryService.getBookById(bookId);
        Author author = libraryService.getAuthorById(book.getAuthor().getId());

        LibraryDto newBook = new LibraryDto(book, author);

        model.addAttribute("book", newBook);

        return "view/book-edit";
    }

    @PostMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long bookId, @ModelAttribute LibraryDto newBook) {
        libraryService.updateBook(bookId, newBook);

        return "redirect:/book-list";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long bookId) {
        libraryService.deleteBook(bookId);

        return "redirect:/book-list";
    }
}


