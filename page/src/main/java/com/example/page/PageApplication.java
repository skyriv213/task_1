package com.example.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class PageApplication {

	public static void main(String[] args) {


		SpringApplication.run(PageApplication.class, args);
	}

}
