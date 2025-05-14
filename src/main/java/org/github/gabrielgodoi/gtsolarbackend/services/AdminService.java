package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.AdminDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.InsertAdminDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public List<AdminDto> findAll() {
        return adminRepository.findAll()
                .stream()
                .map(AdminDto::new)
                .collect(Collectors.toList());
    }

    public AdminDto findById(String id) {
        return adminRepository.findById(id)
                .map(AdminDto::new)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
    }

    public AdminDto create(InsertAdminDto dto) {
        Admin admin = new Admin();
        dtoToEntity(dto, admin);
        admin.setCreated_at(LocalDateTime.now());
        admin.setUpdated_at(LocalDateTime.now());
        return new AdminDto(adminRepository.insert(admin));
    }

    public AdminDto update(String id, InsertAdminDto dto) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        dtoToEntity(dto, admin);
        admin.setUpdated_at(LocalDateTime.now());
        return new AdminDto(adminRepository.save(admin));
    }

    public void delete(String id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        adminRepository.delete(admin);
    }

    public AdminDto deactivate(String id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        admin.setUpdated_at(LocalDateTime.now());
        return new AdminDto(adminRepository.save(admin));
    }

    private void dtoToEntity(InsertAdminDto dto, Admin entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setAdminRole(dto.getAdminRole());
    }
}
