package org.github.gabrielgodoi.gtsolarbackend.services;

import org.github.gabrielgodoi.gtsolarbackend.entities.Admin;
import org.github.gabrielgodoi.gtsolarbackend.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        admin.setCreated_at(LocalDateTime.now());
        admin.setUpdated_at(LocalDateTime.now());
        return adminRepository.save(admin);
    }

    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> findAdminById(String id) {
        return adminRepository.findById(id);
    }

    public Admin updateAdmin(String id, Admin adminDetails) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setName(adminDetails.getName());
        admin.setEmail(adminDetails.getEmail());
        admin.setPassword(adminDetails.getPassword());
        admin.setAdminRole(adminDetails.getAdminRole());
        admin.setUpdated_at(LocalDateTime.now());
        return adminRepository.save(admin);
    }

    public void deleteAdmin(String id) {
        adminRepository.deleteById(id);
    }

    public Admin deactivateAdmin(String id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setActive(false); // Assuming there's an 'active' field in Admin class
        admin.setUpdated_at(LocalDateTime.now());
        return adminRepository.save(admin);
    }
}