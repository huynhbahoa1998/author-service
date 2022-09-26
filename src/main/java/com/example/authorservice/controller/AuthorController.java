package com.example.authorservice.controller;

import com.example.authorservice.dto.AuthorResponse;
import com.example.authorservice.model.Author;
import com.example.authorservice.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> create(@RequestBody Author author) {
        try {
            List<Author> authors = new ArrayList<>();
            authors.add(authorService.create(author));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new AuthorResponse(authors, "Created author successfully!"));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthorResponse(new ArrayList<>(), "Error creating author: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<AuthorResponse> findAll() {
        try {
            List<Author> authors = authorService.findAll();
            String message = "";
            if (authors.isEmpty()) {
                message = "No data found";
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new AuthorResponse(authors, message));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthorResponse(new ArrayList<>(), "Error getting author: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> findById(@PathVariable("id") String uuid) {
        try {
            List<Author> authors = new ArrayList<>();
            String message = "";

            Author author = authorService.findById(uuid);
            if (author != null) {
                authors.add(author);
            } else {
                message = "Author with Uuid = " + uuid + " is not exist";
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new AuthorResponse(authors, message));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthorResponse(new ArrayList<>(), "Error getting author: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable("id") String uuid, @RequestBody Author author) {
        try {
            List<Author> authors = new ArrayList<>();
            String message = "";

            if (authorService.updateAuthor(uuid, author) != null) {
                authors.add(author);
            } else {
                message = "Author with Uuid = " + uuid + " is not exist";
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new AuthorResponse(authors, message));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthorResponse(new ArrayList<>(), "Error updating author: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorResponse> deleteById(@PathVariable("id") String uuid) {
        try {
            List<Author> authors = new ArrayList<>();
            String message = "";

            Author author = authorService.deleteById(uuid);
            if (author != null) {
                authors.add(author);
            } else {
                message = "Author with Uuid = " + uuid + " is not exist";
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new AuthorResponse(authors, message));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthorResponse(new ArrayList<>(), "Error deleting author: " + e.getMessage()));
        }
    }
}
