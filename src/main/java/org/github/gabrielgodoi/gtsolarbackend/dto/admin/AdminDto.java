package org.github.gabrielgodoi.gtsolarbackend.dto.admin;

import lombok.*;
import org.github.gabrielgodoi.gtsolarbackend.entities.admins.Admin;
import org.github.gabrielgodoi.gtsolarbackend.dto.commission.Commission;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private String id;
    private String name;
    private String email;
    private AdminRole adminRole;
    private List<Commission> comissionsList = new ArrayList<>();

    public AdminDto(Admin entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setEmail(entity.getEmail());
        this.setAdminRole(entity.getAdminRole());
        for (Commission e : entity.getComissionsList()) {
            this.getComissionsList().add(e);
        }
    }
}
