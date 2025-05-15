package org.github.gabrielgodoi.gtsolarbackend.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.entities.Client;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
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

    public ClientDto(Client entity) {
        setId(entity.getId());
        setName(entity.getName());
        setEmail(entity.getEmail());
        setPhone(entity.getPhone());
        setDocument(entity.getDocument());
        setStreet(entity.getStreet());
        setHouseNumber(entity.getHouseNumber());
        setComplement(entity.getComplement());
        setNeighbor(entity.getNeighbor());
        setCity(entity.getCity());
        setUf(entity.getUf());
        setCreated_at(entity.getCreated_at());
        setUpdated_at(entity.getUpdated_at());
    }
}
