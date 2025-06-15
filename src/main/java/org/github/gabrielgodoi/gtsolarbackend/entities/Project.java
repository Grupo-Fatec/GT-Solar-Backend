package org.github.gabrielgodoi.gtsolarbackend.entities;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Equipment;
import org.github.gabrielgodoi.gtsolarbackend.entities.admins.Admin;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.*;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "project")
public class Project {
    @Id
    private String id;
    private String name;
    private StatusEnum status;
    private Double energyConsumption;
    private boolean approvedByEngineer;
    private boolean approvedByClient;
    private Double approvedValue;

    @DBRef
    private Admin createdBy;
    @DBRef
    private Engineer engineer;
    @DBRef
    private Installer installer;
    @DBRef
    private Client client;
    @DBRef
    private List<Equipment> equipments = new ArrayList<>();
}
