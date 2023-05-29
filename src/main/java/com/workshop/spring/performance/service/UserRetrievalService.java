package com.workshop.spring.performance.service;

import com.workshop.spring.performance.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserRetrievalService {

    private final RestTemplate restTemplate = new RestTemplate();

    public User retrieveUser() {
        return restTemplate.getForObject("http://localhost:8080/hello/wiremock", User.class);
    }

}
