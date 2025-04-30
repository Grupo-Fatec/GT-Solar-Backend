package org.github.gabrielgodoi.gtsolarbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.dto.details.Details;
import org.github.gabrielgodoi.gtsolarbackend.enums.BudgetStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "budget")
public class Budget {
    @Id
    private String id;
    private Double value;
    private BudgetStatus status;
    private Date date;
    private List<Details> details = new ArrayList<>();
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
