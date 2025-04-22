package org.github.gabrielgodoi.gtsolarbackend.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "client")
public class Client {
    @Id
    private String id;
}
