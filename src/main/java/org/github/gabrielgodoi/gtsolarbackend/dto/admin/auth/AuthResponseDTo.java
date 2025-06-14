package org.github.gabrielgodoi.gtsolarbackend.dto.admin.auth;

public record AuthResponseDTo(
        String name,
        String email,
        String userRole,
        String token
) {
}
