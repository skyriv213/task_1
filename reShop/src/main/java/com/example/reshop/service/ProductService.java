package com.example.reshop.service;

import com.example.reshop.dtos.ItemDto;
import com.example.reshop.dtos.product.ProductMypriceRequestDto;
import com.example.reshop.dtos.product.ProductRequestDto;
import com.example.reshop.dtos.product.ProductResponseDto;
import com.example.reshop.entity.Product;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto requestDto, HttpServletRequest request);


    Page<Product> getProducts(HttpServletRequest request, int page, int size, String sortBy, boolean isAsc);

    Long updateProduct(Long id, ProductMypriceRequestDto requestDto, HttpServletRequest request);

    void updateBySearch(Long id, ItemDto itemDto);

}
