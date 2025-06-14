package org.github.gabrielgodoi.gtsolarbackend.controller;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.equipments.EquipmentsDto;
import org.github.gabrielgodoi.gtsolarbackend.services.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/equipments")
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<EquipmentsDto>> findAll() {
        return ResponseEntity.ok(equipmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentsDto> findOne(@PathVariable String id) {
        return ResponseEntity.ok(equipmentService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<EquipmentsDto> create(@RequestBody EquipmentsDto dto) {
        EquipmentsDto created = equipmentService.create(dto);
        URI uri = URI.create("/equipments/" + created.id());
        return ResponseEntity.created(uri).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentsDto> update(@PathVariable String id, @RequestBody EquipmentsDto dto) {
        return ResponseEntity.ok(equipmentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        equipmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMany(@RequestBody List<String> ids) {
        equipmentService.deleteMany(ids);
        return ResponseEntity.noContent().build();
    }
}
