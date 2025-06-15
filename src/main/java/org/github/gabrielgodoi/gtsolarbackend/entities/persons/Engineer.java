package org.github.gabrielgodoi.gtsolarbackend.entities.persons;

import com.mongodb.lang.Nullable;
import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Engineer extends Person {
    @Indexed(unique = true, sparse = true, name = "crea")
    private String crea;
    private String specialization;
    private Double valuePerKwh;

    public Engineer(String id, String name, String type, String email, List<Project> projects, String crea, String specialization, Double valuePerKwh) {
        super(id, name, type, email, projects);
        this.crea = crea;
        this.specialization = specialization;
        this.valuePerKwh = valuePerKwh;
    }
}
