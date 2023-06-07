package com.workshop.spring.performance.service;

import com.workshop.spring.performance.dto.Username;
import com.workshop.spring.performance.model.User;
import com.workshop.spring.performance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebInputException;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserRetrievalService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public String saveUser() {
        log.debug("Received a request to save a user");
        Username username = restTemplate.getForObject("http://localhost:8080/hello/wiremock", Username.class);
        log.debug("Retrieved the username from API");
        if (username == null || username.name() == null) {
            log.error("User not found or is invalid");
            throw new ServerWebInputException("invalid data");
        }
        userRepository.save(new User(UUID.randomUUID(), username.name()));
        log.debug("User with name {} saved in the DB", username.name());
        return username.name();
    }

}
