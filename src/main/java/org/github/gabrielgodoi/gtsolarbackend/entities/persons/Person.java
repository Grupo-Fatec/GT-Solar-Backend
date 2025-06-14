package org.github.gabrielgodoi.gtsolarbackend.entities.persons;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "person")
public class Person {
    @Id
    private String id;
    private String name;
    private String type;
    @Indexed(unique = true)
    private String email;

    @DBRef
    private List<Project> projects = new ArrayList<>();
}
