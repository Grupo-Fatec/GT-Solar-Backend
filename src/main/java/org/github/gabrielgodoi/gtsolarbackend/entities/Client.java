package org.github.gabrielgodoi.gtsolarbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
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
    private String property;
    private String observations;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
