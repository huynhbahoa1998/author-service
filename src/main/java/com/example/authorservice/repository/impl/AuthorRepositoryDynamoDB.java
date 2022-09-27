package com.example.authorservice.repository.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.example.authorservice.mapper.AuthorMapper;
import com.example.authorservice.model.Author;
import com.example.authorservice.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthorRepositoryDynamoDB implements AuthorRepository {

    private final DynamoDB dynamoDB;

    private final Table table;

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorRepositoryDynamoDB(AmazonDynamoDB amazonDynamoDB, AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
        this.dynamoDB = new DynamoDB(amazonDynamoDB);
        this.table = dynamoDB.getTable(AuthorRepository.TABLE_NAME);
    }

    public Author create(Author author) {
        table.putItem(authorMapper.mapAuthorToItem(author));
        return author;
    }

    public Author findById(String uuid) {
        Item item = table.getItem(AuthorMapper.AUTHOR_UUID, uuid);
        if (item != null) {
            return authorMapper.mapItemToAuthor(item);
        } else {
            return null;
        }
    }

    public Author update(String uuid, Author author) {
        Author oldAuthor = this.findById(uuid);
        if (oldAuthor != null) {
            Map<String, String> expressionAttributeNames = new HashMap<>();
            expressionAttributeNames.put("#FirstName", AuthorMapper.AUTHOR_FIRST_NAME);
            expressionAttributeNames.put("#LastName", AuthorMapper.AUTHOR_LAST_NAME);
            expressionAttributeNames.put("#Email", AuthorMapper.AUTHOR_EMAIL);

            Map<String, Object> expressionAttributeValues = new HashMap<>();
            expressionAttributeValues.put(":firstName", author.getFirstName());
            expressionAttributeValues.put(":lastName", author.getLastName());
            expressionAttributeValues.put(":email", author.getEmail());

            table.updateItem(
                    AuthorMapper.AUTHOR_UUID,
                    uuid,
                    "set #FirstName = :firstName, #LastName = :lastName, #Email = :email",
                    expressionAttributeNames,
                    expressionAttributeValues);

            return author;
        }
        return null;
    }

    public Author deleteById(String uuid) {
        Author author = this.findById(uuid);
        if (author != null) {
            table.deleteItem(AuthorMapper.AUTHOR_UUID, uuid);
        }
        return author;
    }
}
