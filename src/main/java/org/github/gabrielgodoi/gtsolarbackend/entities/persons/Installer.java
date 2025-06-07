package org.github.gabrielgodoi.gtsolarbackend.entities.persons;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Installer extends Person {
    private Double pricePerKwp;
    private String availableDays;
    private boolean isAvailable;
}
