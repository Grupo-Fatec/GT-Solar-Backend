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

    public Client(String id, String name, String phone, String street,
                  String email, String houseNumber, String complement,
                  LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.email = email;
        this.houseNumber = houseNumber;
        this.complement = complement;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

}
