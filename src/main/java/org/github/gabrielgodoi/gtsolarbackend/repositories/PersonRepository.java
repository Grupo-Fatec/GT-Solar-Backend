package org.github.gabrielgodoi.gtsolarbackend.repositories;

import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Engineer;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Installer;
import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findAllByType(String type);

    @Query("{ '_id': ?0, 'type': 'ENGINEER' }")
    Optional<Engineer> findEngineerById(String id);

    @Query("{ '_id': ?0, 'type': 'INSTALLER' }")
    Optional<Installer> findInstallerById(String id);
}
