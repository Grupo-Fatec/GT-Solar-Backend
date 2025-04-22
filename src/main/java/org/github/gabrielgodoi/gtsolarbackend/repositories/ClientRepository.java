package org.github.gabrielgodoi.gtsolarbackend.repositories;

import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<User, String> {
}
