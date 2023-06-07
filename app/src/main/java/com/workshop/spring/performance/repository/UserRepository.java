package com.workshop.spring.performance.repository;

import com.workshop.spring.performance.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
