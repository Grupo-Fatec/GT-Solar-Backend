package org.github.gabrielgodoi.gtsolarbackend.dto.project;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.dto.step.Step;
import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.github.gabrielgodoi.gtsolarbackend.entities.Budget;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProjectDto {
    @Id
    private String id;
    private String name;
    private StatusEnum status;
    private List<Step> steps;
    private Set<String> documents = new HashSet<>();
    private Admin createdBy;
    private List<Budget> budgetList = new ArrayList<>();
    public ProjectDto(Project entity) {
    }
}
