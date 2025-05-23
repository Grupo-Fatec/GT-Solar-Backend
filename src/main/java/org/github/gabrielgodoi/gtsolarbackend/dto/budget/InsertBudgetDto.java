package org.github.gabrielgodoi.gtsolarbackend.dto.budget;

import lombok.*;

import jakarta.annotation.Nullable;
import org.github.gabrielgodoi.gtsolarbackend.dto.details.Details;
import org.github.gabrielgodoi.gtsolarbackend.enums.BudgetStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InsertBudgetDto {
    private BudgetStatus status;

    @Nullable
    private Double approvedValue;

    private List<Details> details = new ArrayList<>();
}
