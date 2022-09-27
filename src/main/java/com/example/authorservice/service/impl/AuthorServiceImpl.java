package com.example.authorservice.service.impl;

import com.example.authorservice.dto.AuthorDTO;
import com.example.authorservice.mapper.AuthorMapper;
import com.example.authorservice.model.Author;
import com.example.authorservice.repository.AuthorRepository;
import com.example.authorservice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public AuthorDTO create(AuthorDTO authorDTO) {
        Author author = authorRepository.create(authorMapper.mapAuthorDTOToAuthor(authorDTO));
        return authorMapper.mapAuthorToAuthorDTO(author);
    }

    @Override
    public AuthorDTO findById(String uuid) {
        Author author = authorRepository.findById(uuid);
        if (author != null) {
            return authorMapper.mapAuthorToAuthorDTO(author);
        }
        return null;
    }


    @Override
    public AuthorDTO update(String uuid, AuthorDTO authorDTO) {
        Author author = authorRepository.update(uuid, authorMapper.mapAuthorDTOToAuthor(authorDTO));
        if (author != null) {
            return authorMapper.mapAuthorToAuthorDTO(author);
        }
        return null;
    }

    @Override
    public AuthorDTO deleteById(String uuid) {
        Author author = authorRepository.deleteById(uuid);
        if (author != null) {
            return authorMapper.mapAuthorToAuthorDTO(author);
        }
        return null;
    }
}
