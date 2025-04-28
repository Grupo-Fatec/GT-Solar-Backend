package org.github.gabrielgodoi.gtsolarbackend.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertAdminDto {
    private String name;
    private String email;
    private String password;
    private AdminRole adminRole;
}
