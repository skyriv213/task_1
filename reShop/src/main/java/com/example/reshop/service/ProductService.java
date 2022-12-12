package com.example.reshop.service;

import com.example.reshop.dtos.ProductMypriceRequestDto;
import com.example.reshop.dtos.ProductRequestDto;
import com.example.reshop.dtos.ProductResponseDto;
import com.example.reshop.entity.Product;
import com.example.reshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(ProductRequestDto requestDto) throws SQLException {
        Product product = new Product(requestDto);
        return productRepository.createProduct(product);

    }

    public List<ProductResponseDto> getProducts() throws SQLException {
        return productRepository.getProducts();
    }

    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = productRepository.getProduct(id);
        if (product == null) {
            throw new NullPointerException("해당상품은 존재하지 않습니다");
        }
        return productRepository.updateProduct(product.getId(), requestDto);

    }
}
