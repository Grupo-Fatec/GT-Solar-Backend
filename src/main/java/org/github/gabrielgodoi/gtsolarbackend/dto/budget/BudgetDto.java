package org.github.gabrielgodoi.gtsolarbackend.dto.budget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.dto.details.Details;
import org.github.gabrielgodoi.gtsolarbackend.dto.project.ProjectDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Budget;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.github.gabrielgodoi.gtsolarbackend.enums.BudgetStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDto {
    private String id;
    private Double value;
    private BudgetStatus status;
    private LocalDateTime date;
    private List<Details> details = new ArrayList<>();
    private String project;

    public BudgetDto(Budget entity) {
        setId(entity.getId());
        setValue(entity.getApprovedValue());
        setStatus(entity.getStatus());
        setDate(entity.getDate());
        setProject(entity.getProject().getName());
        entity.getDetails().forEach(d -> getDetails().add(d));
    }
}
