package org.github.gabrielgodoi.gtsolarbackend.repositories;

import org.github.gabrielgodoi.gtsolarbackend.entities.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetRepository extends MongoRepository<Budget, String> {
    List<Budget> findByProject(Project project);
}
