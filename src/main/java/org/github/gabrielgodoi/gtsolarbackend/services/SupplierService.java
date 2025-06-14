package org.github.gabrielgodoi.gtsolarbackend.services;

import lombok.RequiredArgsConstructor;
import org.github.gabrielgodoi.gtsolarbackend.dto.supplier.InsertSupplierDto;
import org.github.gabrielgodoi.gtsolarbackend.dto.supplier.SupplierDto;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Equipment;
import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Supplier;
import org.github.gabrielgodoi.gtsolarbackend.errors.EntityNotFoundException;
import org.github.gabrielgodoi.gtsolarbackend.repositories.SupplierRepository;
import org.github.gabrielgodoi.gtsolarbackend.services.mappers.SupplierMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper mapper;

    public List<SupplierDto> findAll() {
        return this.supplierRepository.findAll().stream().map(s -> this.mapper.toDto(s)).collect(Collectors.toList());
    }

    public SupplierDto findOne(String id) {
        return this.mapper.toDto(this.supplierRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("supplier with id: " + id + " was not found")
        ));
    }

    public SupplierDto create(InsertSupplierDto supplierDto) {
        return this.mapper.toDto(this.supplierRepository.save(this.mapper.toEntity(supplierDto)));
    }

    public SupplierDto update(String id, InsertSupplierDto supplierDto) {
        Supplier retriviedData = this.supplierRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("supplier with id: " + id + " was not found")
        );

        retriviedData.setName(supplierDto.name() == null ? retriviedData.getName() : supplierDto.name());
        retriviedData.setEmail(supplierDto.email() == null ? retriviedData.getEmail() : supplierDto.email());
        retriviedData.setDeliveryDate(supplierDto.deliveryDate() == null ? retriviedData.getDeliveryDate() : supplierDto.deliveryDate());

       return this.mapper.toDto(this.supplierRepository.save(retriviedData));
    }

    public void delete(String id) {
        this.findOne(id);
        this.supplierRepository.deleteById(id);
    }

    public void deleteMany(List<String> ids) {
        ids.forEach(this::findOne);
        this.supplierRepository.deleteAllById(ids);
    }
}
