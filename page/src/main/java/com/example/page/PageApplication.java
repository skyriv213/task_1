package com.example.page;

import com.example.page.entity.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.net.UnknownServiceException;

@SpringBootApplication
@EnableJpaAuditing
public class PageApplication {

    public static void main(String[] args) {

//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("A");
//
//        entityManager.persist(user);
//
//        entityManager.detach(user);
//        entityManager.clear();
//        entityManager.clear();
//
//            entityManager.remove(user);

        SpringApplication.run(PageApplication.class, args);
    }

}
