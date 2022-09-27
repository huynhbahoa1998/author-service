package com.example.authorservice.controller;

import com.example.authorservice.dto.AuthorDTO;
import com.example.authorservice.dto.AuthorResponse;
import com.example.authorservice.exception.ResourceNotFoundException;
import com.example.authorservice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public AuthorResponse create(@Valid @RequestBody AuthorDTO authorDTO) {
        return new AuthorResponse(authorService.create(authorDTO), "Created author successfully!");
    }

    @GetMapping("/{id}")
    public AuthorResponse findById(@PathVariable("id") String uuid) {
        AuthorDTO authorDTO = authorService.findById(uuid);
        if (authorDTO != null) {
            return new AuthorResponse(authorDTO, "Got author successfully!");
        } else {
            throw new ResourceNotFoundException("Author with Uuid = " + uuid + " is not exist");
        }
    }

    @PutMapping("/{id}")
    public AuthorResponse update(@PathVariable("id") String uuid, @RequestBody AuthorDTO authorDTO) {
        AuthorDTO result = authorService.update(uuid, authorDTO);
        if (result != null) {
            return new AuthorResponse(result, "Updated author successfully!");
        } else {
            throw new ResourceNotFoundException("Author with Uuid = " + uuid + " is not exist");
        }
    }

    @DeleteMapping("/{id}")
    public AuthorResponse deleteById(@PathVariable("id") String uuid) {
        AuthorDTO authorDTO = authorService.deleteById(uuid);
        if (authorDTO != null) {
            return new AuthorResponse(authorDTO, "Deleted author successfully!");
        } else {
            throw new ResourceNotFoundException("Author with Uuid = " + uuid + " is not exist");
        }
    }
}
