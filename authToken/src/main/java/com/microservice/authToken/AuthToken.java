package com.microservice.authToken;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = {"com.microservice.authToken.repository"})
@EnableAsync
@Slf4j
public class AuthToken {

    public static void main(String[] args) {
        SpringApplication.run(AuthToken.class, args);
    }
    @Bean
    public void init() {
        log.trace("INIT");
    }
    @Bean
    public RestTemplate restTesmplate() {
        return new RestTemplate();
    }

}
