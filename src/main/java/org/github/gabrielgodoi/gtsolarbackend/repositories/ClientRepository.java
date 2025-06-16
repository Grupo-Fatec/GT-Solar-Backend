package org.github.gabrielgodoi.gtsolarbackend.repositories;

import org.github.gabrielgodoi.gtsolarbackend.entities.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByEmail(String email);
}
