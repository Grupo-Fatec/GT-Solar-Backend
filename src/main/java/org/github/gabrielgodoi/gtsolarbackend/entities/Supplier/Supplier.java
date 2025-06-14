package org.github.gabrielgodoi.gtsolarbackend.entities.Supplier;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "supplier")
public class Supplier {
    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String email;
    private String deliveryDate;
    private List<Equipment> equipments = new ArrayList<>();
}
