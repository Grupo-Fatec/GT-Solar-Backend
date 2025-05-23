package org.github.gabrielgodoi.gtsolarbackend.repositories;

import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    Optional<List<Project>> findAllByClientId(String clientId);

    Project findByName(String name);
}
