package com.example.authorservice.service;

import com.example.authorservice.dto.AuthorDTO;

public interface AuthorService {

    AuthorDTO create(AuthorDTO authorDTO);

    AuthorDTO findById(String uuid);

    AuthorDTO update(String uuid, AuthorDTO author);

    AuthorDTO deleteById(String uuid);
}
