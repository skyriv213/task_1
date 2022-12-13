package com.example.reshop.service;

import com.example.reshop.dtos.ItemDto;
import com.example.reshop.dtos.ProductMypriceRequestDto;
import com.example.reshop.dtos.ProductRequestDto;
import com.example.reshop.dtos.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequestDto requestDto);

    List<ProductResponseDto> getProducts();

    Long updateProduct(Long id, ProductMypriceRequestDto requestDto);

    void updatedBySearch(Long id, ItemDto itemDto);

}
