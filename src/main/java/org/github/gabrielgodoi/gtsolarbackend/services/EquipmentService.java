package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.equipments.EquipmentsDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Equipment;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Supplier;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.EquipmentRepository;
import org.github.gabrielgodoi.gtsolarbackend.repositories.SupplierRepository;
import org.github.gabrielgodoi.gtsolarbackend.services.mappers.EquipmentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final SupplierRepository supplierRepository;
    private final EquipmentMapper mapper;

    public List<EquipmentsDto> findAll() {
        return this.equipmentRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public EquipmentsDto findOne(String id) {
        return this.mapper.toDto(this.equipmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("equipment with id: " + id + " was not found")
        ));
    }

    public EquipmentsDto create(EquipmentsDto dto) {
        Equipment equipment = this.mapper.toEntity(dto);
        return this.mapper.toDto(this.equipmentRepository.save(equipment));
    }

    public EquipmentsDto update(String id, EquipmentsDto dto) {
        Supplier supplier = this.supplierRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("supplier with id: " + id + " was not found")
        );
        Equipment retrieved = this.equipmentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("equipment with id: " + id + " was not found")
        );

        retrieved.setName(dto.name() == null ? retrieved.getName() : dto.name());
        retrieved.setSupplier(dto.supplierId() == null ? retrieved.getSupplier() : supplier);
        retrieved.setPower(dto.power() == null ? retrieved.getPower() : dto.power());
        retrieved.setPrice(dto.price() == null ? retrieved.getPrice() : dto.price());

        return this.mapper.toDto(this.equipmentRepository.save(retrieved));
    }

    public void delete(String id) {
        this.findOne(id);
        this.equipmentRepository.deleteById(id);
    }

    public void deleteMany(List<String> ids) {
        ids.forEach(this::findOne);
        this.equipmentRepository.deleteAllById(ids);
    }
}
