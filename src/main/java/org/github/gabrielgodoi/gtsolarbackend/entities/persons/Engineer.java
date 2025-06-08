package org.github.gabrielgodoi.gtsolarbackend.entities.persons;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Engineer extends Person {
    @Indexed(unique = true)
    private String crea;
    private String specialization;
    private Double valuePerKwp;

    public Engineer(String id, String name, String type, String email, List<Project> projects, String crea, String specialization, Double valuePerKwp) {
        super(id, name, type, email, projects);
        this.crea = crea;
        this.specialization = specialization;
        this.valuePerKwp = valuePerKwp;
    }
}
