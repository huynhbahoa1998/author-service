package com.example.authorservice.service;

import com.example.authorservice.model.Author;

import java.util.List;

public interface AuthorService {

    Author create(Author author);

    List<Author> findAll();

    Author findById(String uuid);

    Author updateAuthor(String uuid, Author author);

    Author deleteById(String uuid);
}
