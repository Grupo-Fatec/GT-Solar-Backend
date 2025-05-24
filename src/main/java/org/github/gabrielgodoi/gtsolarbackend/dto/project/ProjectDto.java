package org.github.gabrielgodoi.gtsolarbackend.dto.project;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.BudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.step.Step;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;
import org.springframework.data.annotation.Id;

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
    private List<Step> steps = new ArrayList<>();
    private Set<String> documents = new HashSet<>();
    private String createdBy;
    private String client;
    private Double approvedValue;
    private BudgetDto budgetDto;
}
