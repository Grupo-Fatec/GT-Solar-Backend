package org.github.gabrielgodoi.gtsolarbackend.controller;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.AdminDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.InsertAdminDto;
import org.github.gabrielgodoi.gtsolarbackend.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDto> create(@RequestBody InsertAdminDto insertDto) {
        AdminDto created = adminService.create(insertDto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getById(@PathVariable String id) {
        AdminDto admin = adminService.findById(id);
        return ResponseEntity.ok(admin);
    }
    @GetMapping
    public ResponseEntity<List<AdminDto>> getAll() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> update(@PathVariable String id, @RequestBody InsertAdminDto updateDto) {
        AdminDto updated = adminService.update(id, updateDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        adminService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<AdminDto> deactivate(@PathVariable String id) {
        AdminDto deactivated = adminService.deactivate(id);
        return ResponseEntity.ok(deactivated);
    }
}

