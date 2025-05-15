package org.github.gabrielgodoi.gtsolarbackend.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.dto.step.Step;
import org.github.gabrielgodoi.gtsolarbackend.entities.Budget;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertProjectDto {
    private String id;
    private String name;
    private StatusEnum status;
    private List<Step> steps;
    private Set<String> documents = new HashSet<>();
    private List<Budget> budgetList = new ArrayList<>();
    private String createdBy;
    private String clientID;
}
