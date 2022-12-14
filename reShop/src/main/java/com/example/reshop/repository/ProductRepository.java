package com.example.reshop.repository;

import com.example.reshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUserId(Long userId, Pageable pageable);

    Page<Product> findAll(Pageable pageable);
    Optional<Product> findByIdAndUserId(Long id, Long userId);
}
