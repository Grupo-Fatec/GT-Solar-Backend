package org.github.gabrielgodoi.gtsolarbackend.dto.budget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.dto.details.Details;
import org.github.gabrielgodoi.gtsolarbackend.entities.Budget;
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
    private Double approvedValue;
    private BudgetStatus status;
    private LocalDateTime date;
    private List<Details> details = new ArrayList<>();
    private String project;
}
