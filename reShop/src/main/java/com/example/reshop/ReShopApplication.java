package com.example.reshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReShopApplication.class, args);
    }

}
