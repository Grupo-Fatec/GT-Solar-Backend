package org.github.gabrielgodoi.gtsolarbackend.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "admin")
public class Admin {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private AdminRole adminRole;
    private List<Commission> comissionsList = new ArrayList<>();
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Admin(String id, String name, String email, String password, AdminRole adminRole, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.adminRole = adminRole;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
