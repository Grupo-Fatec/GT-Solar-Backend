package org.github.gabrielgodoi.gtsolarbackend.controller;

import org.github.gabrielgodoi.gtsolarbackend.dto.admin.AdminDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public ResponseEntity<List<AdminDto>> findAll(){}
}
