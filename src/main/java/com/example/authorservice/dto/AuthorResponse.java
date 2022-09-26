package com.example.authorservice.dto;

import com.example.authorservice.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {

    private List<Author> authors;

    private String message;
}
