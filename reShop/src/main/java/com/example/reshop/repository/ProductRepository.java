package com.example.reshop.repository;

import com.example.reshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    ProductResponseDto createProduct(Product product) throws SQLException;
//
//    List<ProductResponseDto> getProducts() throws SQLException;
//
//    Long updateProduct(Long id, ProductMypriceRequestDto productMypriceRequestDto) throws SQLException;
//
//    Product getProduct(Long id) throws SQLException;
}
