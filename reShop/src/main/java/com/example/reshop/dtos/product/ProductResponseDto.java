package com.example.reshop.dtos.product;

import com.example.reshop.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String title;
    private String image;
    private String link;
    private int lprice;
    private int myprice;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.image = product.getImage();
        this.link = product.getLink();
        this.lprice = product.getLprice();
        this.myprice = product.getMyprice();
    }

}
