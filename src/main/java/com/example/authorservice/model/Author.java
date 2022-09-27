package com.example.authorservice.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "Author")
public class Author {

    @DynamoDBHashKey(attributeName = "Uuid")
    private String uuid;

    @DynamoDBAttribute(attributeName = "FirstName")
    private String firstName;

    @DynamoDBAttribute(attributeName = "LastName")
    private String lastName;

    @DynamoDBAttribute(attributeName = "Email")
    private String email;
}
