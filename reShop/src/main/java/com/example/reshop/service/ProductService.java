package com.example.reshop.service;

import com.example.reshop.dtos.ItemDto;
import com.example.reshop.dtos.product.ProductMypriceRequestDto;
import com.example.reshop.dtos.product.ProductRequestDto;
import com.example.reshop.dtos.product.ProductResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto requestDto, HttpServletRequest request);


    List<ProductResponseDto> getProducts(HttpServletRequest request);

    Long updateProduct(Long id, ProductMypriceRequestDto requestDto, HttpServletRequest request);

    void updatedBySearch(Long id, ItemDto itemDto);

}
