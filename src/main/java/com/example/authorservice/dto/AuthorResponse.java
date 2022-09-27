package com.example.authorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {

    private AuthorDTO authorDTO;

    private String message;
}
