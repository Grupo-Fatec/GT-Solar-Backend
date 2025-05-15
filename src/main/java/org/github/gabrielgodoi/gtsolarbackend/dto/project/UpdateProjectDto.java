package org.github.gabrielgodoi.gtsolarbackend.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.enums.StatusEnum;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProjectDto {
    private String name;
    private StatusEnum status;
    private List<String> observations = new ArrayList<>();
}
