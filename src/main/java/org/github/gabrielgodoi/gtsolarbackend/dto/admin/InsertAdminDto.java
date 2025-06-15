package org.github.gabrielgodoi.gtsolarbackend.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;

public record InsertAdminDto(
        @Min(4)
        String name,
        @Email
        String email,
        @Min(5)
        String password,
        AdminRole adminRole
) {}
