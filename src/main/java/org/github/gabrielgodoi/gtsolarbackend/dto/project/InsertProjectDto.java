package org.github.gabrielgodoi.gtsolarbackend.dto.project;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.InsertBudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.step.Step;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsertProjectDto {
    private String id;
    private String name;
    private StatusEnum status;
    private List<Step> steps = new ArrayList<>();
    private Set<String> documents = new HashSet<>();
    private InsertBudgetDto budget;
    private String clientId;
}