package com.example.reshop.controller;

import com.example.reshop.dtos.product.ProductMypriceRequestDto;
import com.example.reshop.dtos.product.ProductRequestDto;
import com.example.reshop.dtos.product.ProductResponseDto;
import com.example.reshop.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    // 관심 상품 등록하기
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto,HttpServletRequest request) throws SQLException {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        return productService.createProduct(requestDto,request);

    }

    // 관심 상품 조회하기
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts(HttpServletRequest request) throws SQLException {
        return productService.getProducts(request);

    }

    // 관심 상품 최저가 등록하기
    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto, HttpServletRequest request) throws SQLException {
        return productService.updateProduct(id, requestDto,request);

    }

}
