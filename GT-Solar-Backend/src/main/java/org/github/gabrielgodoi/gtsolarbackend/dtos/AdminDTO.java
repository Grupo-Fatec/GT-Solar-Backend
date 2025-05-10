package org.github.gabrielgodoi.gtsolarbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private String id;
    private String name;
    private String email;
    private String password;
    private String adminRole;
    private List<String> commissionsList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}