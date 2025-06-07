package org.github.gabrielgodoi.gtsolarbackend.repositories;

import org.github.gabrielgodoi.gtsolarbackend.entities.persons.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findAllByType(String type);
}
