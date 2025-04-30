package org.github.gabrielgodoi.gtsolarbackend.dto.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.enums.BudgetStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Details {
    private String title;
    private String description;
    private Double price;
    private BudgetStatus budgetStatus;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
