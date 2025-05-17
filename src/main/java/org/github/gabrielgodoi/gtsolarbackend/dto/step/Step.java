package org.github.gabrielgodoi.gtsolarbackend.dto.step;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Step {
    private String name;
    private StatusEnum status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<String> observations;
    private Boolean completed;
}
