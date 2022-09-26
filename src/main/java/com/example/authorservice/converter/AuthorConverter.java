package com.example.authorservice.converter;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.example.authorservice.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    public final String AUTHOR_UUID = "Uuid";

    public final String AUTHOR_FIRST_NAME = "FirstName";

    public final String AUTHOR_LAST_NAME = "LastName";

    public final String AUTHOR_EMAIL = "Email";

    public Item convertAuthorToItem(Author author) {
        return new Item()
                .withPrimaryKey(AUTHOR_UUID, author.getUuid())
                .withString(AUTHOR_FIRST_NAME, author.getFirstName())
                .withString(AUTHOR_LAST_NAME, author.getLastName())
                .withString(AUTHOR_EMAIL, author.getEmail());
    }

    public Author convertItemToAuthor(Item item) {
        Author author = new Author();
        author.setUuid(item.getString(AUTHOR_UUID));
        author.setFirstName(item.getString(AUTHOR_FIRST_NAME));
        author.setLastName(item.getString(AUTHOR_LAST_NAME));
        author.setEmail(item.getString(AUTHOR_EMAIL));
        return author;
    }
}
