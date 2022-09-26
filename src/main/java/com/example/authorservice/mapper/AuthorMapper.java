package com.example.authorservice.mapper;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.example.authorservice.converter.AuthorConverter;
import com.example.authorservice.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AuthorMapper {

    private final String TABLE_NAME = "Author";

    private final DynamoDB dynamoDB;

    private final Table table;

    private final AuthorConverter authorConverter;

    @Autowired
    public AuthorMapper(AmazonDynamoDB amazonDynamoDB, AuthorConverter authorConverter) {
        this.authorConverter = authorConverter;
        this.dynamoDB = new DynamoDB(amazonDynamoDB);
        this.table = dynamoDB.getTable(TABLE_NAME);
    }

    public Author create(Author author) {
        table.putItem(authorConverter.convertAuthorToItem(author));
        return author;
    }

    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        table.scan().forEach(item -> authors.add(authorConverter.convertItemToAuthor(item)));
        return authors;
    }

    public Author findById(String uuid) {
        Item item = table.getItem(authorConverter.AUTHOR_UUID, uuid);
        if (item != null) {
            return authorConverter.convertItemToAuthor(item);
        } else {
            return null;
        }
    }

    public Author update(String uuid, Author author) {
        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#F", authorConverter.AUTHOR_FIRST_NAME);
        expressionAttributeNames.put("#L", authorConverter.AUTHOR_LAST_NAME);
        expressionAttributeNames.put("#E", authorConverter.AUTHOR_EMAIL);

        Map<String, Object> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":f", author.getFirstName());
        expressionAttributeValues.put(":l", author.getLastName());
        expressionAttributeValues.put(":e", author.getEmail());

        table.updateItem(
                authorConverter.AUTHOR_UUID,
                uuid,
                "set #F = :f, #L = :l, #E = :e",
                expressionAttributeNames,
                expressionAttributeValues);

        return author;
    }

    public Author deleteById(String uuid) {
        Author author = this.findById(uuid);
        if (author != null) {
            table.deleteItem(authorConverter.AUTHOR_UUID, uuid);
        }
        return author;
    }
}
