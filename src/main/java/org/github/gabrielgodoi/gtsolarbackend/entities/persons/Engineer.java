package org.github.gabrielgodoi.gtsolarbackend.entities.persons;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Engineer extends Person {
    @Indexed(unique = true)
    private String crea;
    private String specialization;
    private Double valuePerProject;
}
