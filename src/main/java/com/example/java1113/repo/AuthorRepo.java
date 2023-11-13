package com.example.java1113.repo;

import com.example.java1113.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    public Author findByName(String name);
}