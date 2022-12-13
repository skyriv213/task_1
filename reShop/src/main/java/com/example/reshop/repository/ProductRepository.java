package com.example.reshop.repository;

import com.example.reshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //    ProductResponseDto createProduct(Product product) throws SQLException;
//
//    List<ProductResponseDto> getProducts() throws SQLException;
//
//    Long updateProduct(Long id, ProductMypriceRequestDto productMypriceRequestDto) throws SQLException;
//
//    Product getProduct(Long id) throws SQLException;
    List<Product> findAllByUserId(Long userId);

    Optional<Product> findByIdAndUserId(Long id, Long userId);
}
