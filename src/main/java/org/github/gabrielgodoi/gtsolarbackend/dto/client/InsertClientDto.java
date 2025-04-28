package org.github.gabrielgodoi.gtsolarbackend.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertClientDto {
    private String name;
    private String address;
    private String cep;
    private String uf;
    private String neighbor;
    private String complement;
    private String email;
    private String phone;
    private String document;
}
