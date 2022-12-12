package com.example.reshop.repository;

import com.example.reshop.dtos.ProductMypriceRequestDto;
import com.example.reshop.dtos.ProductResponseDto;
import com.example.reshop.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository {
    ProductResponseDto createProduct(Product product) throws SQLException;

    List<ProductResponseDto> getProducts() throws SQLException;

    Long updateProduct(Long id, ProductMypriceRequestDto productMypriceRequestDto) throws SQLException;

    Product getProduct(Long id) throws SQLException;
}
