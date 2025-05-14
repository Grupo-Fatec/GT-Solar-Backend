package org.github.gabrielgodoi.gtsolarbackend.dto.admin;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertAdminDto {
    private String name;
    private String email;
    private String password;
    private AdminRole adminRole;
}

