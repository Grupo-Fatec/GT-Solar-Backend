package org.github.gabrielgodoi.gtsolarbackend.dto.details;

import jakarta.annotation.Nullable;
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

    @Nullable
    private LocalDateTime created_at;
    @Nullable
    private LocalDateTime updated_at;
}
