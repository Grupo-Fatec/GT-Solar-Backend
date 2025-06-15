package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.AdminDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.InsertAdminDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.admins.Admin;
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
                .map(this.adminMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public AdminDto findById(String id) {
        return this.adminMapper.entityToDto(adminRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Admin not found")));
    }

    public Admin findUserByEmail(String email) {
        return this.adminRepository.findUserByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("User with email: " + email + " was not found")
        );
    }

    public AdminDto create(InsertAdminDto dto) {
        if (this.adminRepository.findByEmail(dto.email()) != null) {
            throw new AlreadyExistsException("User with email " + dto.email() + " already exists");
        }
        String encPass = new BCryptPasswordEncoder().encode(dto.password());
        InsertAdminDto encryptedDto = new InsertAdminDto(
                dto.name(),
                dto.email(),
                encPass,
                dto.adminRole()
        );
        Admin admin = this.adminMapper.dtoToEntity(encryptedDto);
        admin.setCreated_at(LocalDateTime.now());
        admin.setUpdated_at(LocalDateTime.now());
        return this.adminMapper.entityToDto(this.adminRepository.save(admin));
    }


    public AdminDto update(String id, InsertAdminDto dto) {
        Admin retriviedData = adminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Admin not found")
                );
        retriviedData.setName(dto.name() == null ? retriviedData.getName() : dto.name());
        retriviedData.setEmail(dto.email() == null ? retriviedData.getEmail() : dto.email());
        retriviedData.setAdminRole(dto.email() == null ? retriviedData.getAdminRole() : dto.adminRole());
        ;
        return this.adminMapper.entityToDto(this.adminRepository.save(retriviedData));
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
        return this.adminMapper.entityToDto(adminRepository.save(admin));
    }
}
