package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.AdminDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.InsertAdminDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.github.gabrielgodoi.gtsolarbackend.errors.AlreadyExistsException;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.AdminRepository;
import org.github.gabrielgodoi.gtsolarbackend.services.mappers.AdminMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public List<AdminDto> findAll() {
        return adminRepository.findAll()
                .stream()
                .map(AdminDto::new)
                .collect(Collectors.toList());
    }

    public AdminDto findById(String id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        return new AdminDto(admin);
    }

    public Admin findUserByEmail(String email){
        return this.adminRepository.findUserByEmail(email);
    }

    public AdminDto create(InsertAdminDto dto) {
        if (this.adminRepository.findByEmail(dto.getEmail()) != null){
            throw new AlreadyExistsException("User with email " + dto.getEmail() + " already exists");
        }
        String encPass = new BCryptPasswordEncoder().encode(dto.getPassword());
        dto.setPassword(encPass);
        Admin admin = new Admin();
        dtoToEntity(dto, admin);
        admin.setCreated_at(LocalDateTime.now());
        admin.setUpdated_at(LocalDateTime.now());

        return this.adminMapper.entityToDto(this.adminRepository.save(admin));
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
