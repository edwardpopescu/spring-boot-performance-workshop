package com.workshop.spring.performance.service;

import com.workshop.spring.performance.dto.Username;
import com.workshop.spring.performance.model.User;
import com.workshop.spring.performance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebInputException;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserRetrievalService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public String saveUser() {
        Username username = restTemplate.getForObject("http://localhost:8080/hello/wiremock", Username.class);
        if (username == null || username.name() == null) {
            throw new ServerWebInputException("invalid data");
        }
        userRepository.save(new User(UUID.randomUUID(), username.name()));
        return username.name();
    }

}
