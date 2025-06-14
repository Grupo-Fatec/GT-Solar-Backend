package org.github.gabrielgodoi.gtsolarbackend.controller;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.supplier.*;
import org.github.gabrielgodoi.gtsolarbackend.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<SupplierDto>> findAll() {
        List<SupplierDto> suppliers = supplierService.findAll();
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping
    public ResponseEntity<SupplierDto> create(@RequestBody InsertSupplierDto dto) {
        SupplierDto createdSupplier = supplierService.create(dto);
        URI location = URI.create("/suppliers/" + createdSupplier.id());
        return ResponseEntity.created(location).body(createdSupplier);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDto> findById(@PathVariable("id") String id) {
        SupplierDto supplier = supplierService.findOne(id);
        return ResponseEntity.ok(supplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDto> update(@PathVariable("id") String id, @RequestBody InsertSupplierDto dto) {
        SupplierDto updatedSupplier = supplierService.update(id, dto);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
