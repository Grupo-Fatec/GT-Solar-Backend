package org.github.gabrielgodoi.gtsolarbackend.repositories;

import org.github.gabrielgodoi.gtsolarbackend.entities.Supplier.Equipment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EquipmentRepository extends MongoRepository<Equipment, String>{
}
