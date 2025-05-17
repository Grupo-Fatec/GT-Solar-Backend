package org.github.gabrielgodoi.gtsolarbackend.entities;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.dto.step.Step;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private List<Step> steps = new ArrayList<>();

    @DBRef
    private Admin createdBy;

    @DBRef
    private Client client;

    @DBRef
    private List<Budget> budgetList = new ArrayList<>();
    private List<String> observations = new ArrayList<>();
    private Set<String> documents = new HashSet<>();

    private Double approvedValue;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
