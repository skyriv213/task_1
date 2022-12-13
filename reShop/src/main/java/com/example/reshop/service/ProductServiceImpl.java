package com.example.reshop.service;

import com.example.reshop.dtos.ItemDto;
import com.example.reshop.dtos.ProductMypriceRequestDto;
import com.example.reshop.dtos.ProductRequestDto;
import com.example.reshop.dtos.ProductResponseDto;
import com.example.reshop.entity.Product;
import com.example.reshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = productRepository.saveAndFlush(new Product(requestDto));
        return new ProductResponseDto(product);

    }
    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts() {
        List<ProductResponseDto> list = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            list.add(new ProductResponseDto(product));
        }
        return list;
    }
    @Override
    @Transactional
    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto){
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당상품은 존재하지않습니다"));
        product.update(requestDto);
        return product.getId();
    }

    @Override
    @Transactional
    public void updatedBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 상품은 존재하지 않습니다"));
        product.updateByItemDto(itemDto);

    }
}
