package com.example.authorservice.mapper;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.example.authorservice.dto.AuthorDTO;
import com.example.authorservice.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public static final String AUTHOR_UUID = "Uuid";

    public static final String AUTHOR_FIRST_NAME = "FirstName";

    public static final String AUTHOR_LAST_NAME = "LastName";

    public static final String AUTHOR_EMAIL = "Email";

    public Item mapAuthorToItem(Author author) {
        return new Item()
                .withPrimaryKey(AUTHOR_UUID, author.getUuid())
                .withString(AUTHOR_FIRST_NAME, author.getFirstName())
                .withString(AUTHOR_LAST_NAME, author.getLastName())
                .withString(AUTHOR_EMAIL, author.getEmail());
    }

    public Author mapItemToAuthor(Item item) {
        return Author.builder()
                .uuid(item.getString(AUTHOR_UUID))
                .firstName(item.getString(AUTHOR_FIRST_NAME))
                .lastName(item.getString(AUTHOR_LAST_NAME))
                .email(item.getString(AUTHOR_EMAIL))
                .build();
    }

    public AuthorDTO mapAuthorToAuthorDTO(Author author) {
        return AuthorDTO.builder()
                .uuid(author.getUuid())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .email(author.getEmail())
                .build();
    }

    public Author mapAuthorDTOToAuthor(AuthorDTO authorDTO) {
        return Author.builder()
                .uuid(authorDTO.getUuid())
                .firstName(authorDTO.getFirstName())
                .lastName(authorDTO.getLastName())
                .email(authorDTO.getEmail())
                .build();
    }
}
