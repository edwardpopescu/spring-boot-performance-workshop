package com.workshop.spring.performance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("users")
public record User(@Id UUID id, String name) {
}
