package org.github.gabrielgodoi.gtsolarbackend.dto.admin;

import jakarta.validation.constraints.Email;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;

public record AdminDto(
        String id,
        String name,
        @Email
        String email,
        AdminRole adminRole
) {}
