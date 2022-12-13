package com.example.reshop.service;

import com.example.reshop.dtos.ItemDto;
import com.example.reshop.dtos.product.ProductMypriceRequestDto;
import com.example.reshop.dtos.product.ProductRequestDto;
import com.example.reshop.dtos.product.ProductResponseDto;
import com.example.reshop.entity.Product;
import com.example.reshop.entity.user.User;
import com.example.reshop.entity.user.UserRoleEnum;
import com.example.reshop.repository.ProductRepository;
import com.example.reshop.repository.UserRepository;
import com.example.reshop.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        if (token != null) {
            User user = findUser(token);
            Product product = productRepository.saveAndFlush(new Product(requestDto, user.getId()));
            return new ProductResponseDto(product);
        }else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            User user = findUser(token);
            UserRoleEnum userRoleEnum = user.getRole();
            System.out.println("role = " + userRoleEnum);

            List<ProductResponseDto> list = new ArrayList<>();
            List<Product> productList;

            if (userRoleEnum == UserRoleEnum.USER) {
                productList = productRepository.findAllByUserId(user.getId());
            } else {
                productList = productRepository.findAll();
            }
            for (Product product : productList) {
                list.add(new ProductResponseDto(product));
            }
            return list;
        } else {
            return null;
        }
    }

    private User findUser(String token) {
        Claims claims;
        if (jwtUtil.validateToken(token)) {
            claims = jwtUtil.getUserInfoFromToken(token);
        } else {
            throw new IllegalArgumentException("Token Error");
        }
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
        );
        return user;
    }


    @Override
    @Transactional
    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto,HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        if (token != null) {
            User user = findUser(token);
            Product product = productRepository.findByIdAndUserId(id, user.getId()).orElseThrow(() -> new NullPointerException("해당 상품은 존재하지 않습니다"));
            product.update(requestDto);
            return product.getId();
        }
        else {
            return null;
        }
    }

    @Override
    @Transactional
    public void updatedBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NullPointerException("해당 상품은 존재하지 않습니다"));
        product.updateByItemDto(itemDto);

    }
}
