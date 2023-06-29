package com.workshop.spring.performance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringBootPerformanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPerformanceApplication.class, args);
	}

}
