package com.example.authorservice.service.impl;

import com.example.authorservice.mapper.AuthorMapper;
import com.example.authorservice.model.Author;
import com.example.authorservice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public Author create(Author author) {
        return authorMapper.create(author);
    }

    @Override
    public List<Author> findAll() {
        return authorMapper.findAll();
    }

    @Override
    public Author findById(String uuid) {
        return authorMapper.findById(uuid);
    }


    @Override
    public Author updateAuthor(String uuid, Author author) {
        return authorMapper.update(uuid, author);
    }

    @Override
    public Author deleteById(String uuid) {
        return authorMapper.deleteById(uuid);
    }
}
