package com.example.authorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {

    private String uuid;

    private String firstName;

    private String lastName;

    @Email
    private String email;
}
