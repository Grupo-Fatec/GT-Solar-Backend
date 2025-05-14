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
    private String street;
    private String energy_consumption;
    private String email;
    private String phone;
    private String document;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public ClientDto(Client entity) {
        setId(entity.getId());
        setName(entity.getName());
        setStreet(entity.getStreet());
        setEnergy_consumption(entity.getNeighbor());
        setEmail(entity.getEmail());
        setPhone(entity.getPhone());
        setDocument(entity.getDocument());
        setCreated_at(entity.getCreated_at());
        setUpdated_at(entity.getUpdated_at());
    }
}
