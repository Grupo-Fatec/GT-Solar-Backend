package org.github.gabrielgodoi.gtsolarbackend.entities.Supplier;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.enums.EquipmentType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "equipament")
public class Equipment {
    @Id
    private String id;
    private String name;
    private EquipmentType type;
    private String power;
    private Double price;
    private String guarantee;

    @DBRef
    private Supplier supplier;
}
