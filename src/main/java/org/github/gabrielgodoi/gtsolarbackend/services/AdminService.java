package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.InsertAdminDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;


    public List<Admin> findAll() {
        return this.adminRepository.findAll();
    }

    public Admin findOne(String id) {
        return this.adminRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public Admin create(InsertAdminDto adminDto) {
        Admin admin = new Admin();
        this.dtoToEntity(adminDto, admin);
        return this.adminRepository.insert(admin);
    }

    public void dtoToEntity(InsertAdminDto dto, Admin entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setUpdated_at(LocalDateTime.now());
        entity.setCreated_at(LocalDateTime.now());
    }
}
