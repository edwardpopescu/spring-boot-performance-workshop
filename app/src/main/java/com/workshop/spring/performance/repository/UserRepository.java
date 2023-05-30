package com.workshop.spring.performance.repository;

import com.workshop.spring.performance.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
