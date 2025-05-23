package org.github.gabrielgodoi.gtsolarbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.dto.budget.InsertBudgetDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.details.Details;
import org.github.gabrielgodoi.gtsolarbackend.enums.BudgetStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "budget")
public class Budget {
    @Id
    private String id;
    private Double approvedValue;
    private BudgetStatus status;
    private LocalDateTime date;
    private List<Details> details = new ArrayList<>();

    @DBRef
    private Project project;
}
