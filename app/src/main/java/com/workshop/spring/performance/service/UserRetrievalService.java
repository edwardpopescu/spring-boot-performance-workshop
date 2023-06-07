package com.workshop.spring.performance.service;

import com.workshop.spring.performance.dto.HelloResponse;
import com.workshop.spring.performance.dto.Username;
import com.workshop.spring.performance.model.User;
import com.workshop.spring.performance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserRetrievalService {

    private static final String DEFAULT_GREETING = "Hello";

    private final UserRepository userRepository;
    private final HttpClient defaultHttpClient;

    public Mono<HelloResponse> saveUser() {
        log.debug("Received a request to save a user");
        WebClient usernameWebClient = constructWebClient();
        Mono<String> username = usernameWebClient
                .get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Username.class)
                .map(Username::name)
                .switchIfEmpty(Mono.error(new ServerWebInputException("invalid data")));
        Mono<User> user = username.flatMap(name -> userRepository.save(new User(UUID.randomUUID(), name)));
        return user.flatMap(usr -> Mono.just(new HelloResponse(DEFAULT_GREETING, usr.name())));
    }

    private WebClient constructWebClient() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(defaultHttpClient))
                .baseUrl("http://localhost:8080/hello/wiremock")
                .build();
    }

}
