package org.github.gabrielgodoi.gtsolarbackend.entities.persons;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.entities.Project;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Installer extends Person {
    private Double pricePerKwp;
    private String availableDays;
    private boolean isAvailable;

    public Installer(String id, String name, String type, String email, List<Project> projects, Double pricePerKwp, String availableDays, boolean isAvailable) {
        super(id, name, type, email, projects);
        this.pricePerKwp = pricePerKwp;
        this.availableDays = availableDays;
        this.isAvailable = isAvailable;
    }
}
