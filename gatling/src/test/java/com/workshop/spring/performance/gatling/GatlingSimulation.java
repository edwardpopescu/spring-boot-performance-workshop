package com.workshop.spring.performance.gatling;

import io.gatling.javaapi.core.CoreDsl;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpDsl;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.http.HttpDsl.status;

public class GatlingSimulation extends Simulation {

    private static final int USERS_PER_SEC = 500;
    private static final int DURATION_SECONDS = 600;

    HttpProtocolBuilder httpSetup = HttpDsl.http
            .baseUrl("http://localhost:8082")
            .acceptHeader("application/json")
            .userAgentHeader("Gatling");

    ScenarioBuilder scn = CoreDsl.scenario("Load Test Creating Customers")
            .exec(HttpDsl.http("springboot")
                    .get("/hello/spring")
                    .check(status().is(200))
            );

    public GatlingSimulation() {
        this.setUp(scn.injectOpen(rampUsersPerSec(USERS_PER_SEC/10.0).to(USERS_PER_SEC).during(DURATION_SECONDS)))
                .protocols(httpSetup);
    }
}