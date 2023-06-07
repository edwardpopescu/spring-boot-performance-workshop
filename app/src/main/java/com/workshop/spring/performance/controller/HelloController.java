package com.workshop.spring.performance.controller;

import com.workshop.spring.performance.dto.HelloResponse;
import com.workshop.spring.performance.service.UserRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("hello")
public class HelloController {


    private final UserRetrievalService userRetrievalService;

    @GetMapping(value = "spring",  produces = APPLICATION_JSON_VALUE)
    public Mono<HelloResponse> greeting() {return userRetrievalService.saveUser();
    }

}
