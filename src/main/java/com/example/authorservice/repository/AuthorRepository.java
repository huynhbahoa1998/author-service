package com.example.authorservice.repository;

import com.example.authorservice.model.Author;

public interface AuthorRepository {

    String TABLE_NAME = "Author";

    Author create(Author author);

    Author findById(String uuid);

    Author update(String uuid, Author author);

    Author deleteById(String uuid);
}
