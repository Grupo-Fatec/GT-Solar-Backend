package org.github.gabrielgodoi.gtsolarbackend.controller;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.admin.AdminDto;
import org.github.gabrielgodoi.gtsolarbackend.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<List<AdminDto>> findAll(){
        List<AdminDto> adminDtoList = this.adminService.findAll();
        return ResponseEntity.ok().body(adminDtoList);
    }
}
