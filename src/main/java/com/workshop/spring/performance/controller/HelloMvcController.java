package com.workshop.spring.performance.controller;

import com.workshop.spring.performance.model.User;
import com.workshop.spring.performance.service.UserRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("hello/mvc")
public class HelloMvcController {

    private final UserRetrievalService userRetrievalService;

    @GetMapping(value = "/",  produces = APPLICATION_JSON_VALUE)
    public User greeting() {return userRetrievalService.retrieveUser();
    }

}
