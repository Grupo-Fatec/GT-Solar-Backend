package org.github.gabrielgodoi.gtsolarbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "client")
public class Client {
    @Id
    private String id;
    private String name;
    private String document;
    @Indexed(unique = true, sparse = true)
    private String email;
    private String phone;
    private String street;
    private String houseNumber;
    private String complement;
    private String neighbor;
    private String city;
    private String uf;
    private String cep;
    private String roofType;
    private String propertyType;
    private String observations;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
