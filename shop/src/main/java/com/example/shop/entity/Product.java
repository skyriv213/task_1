package com.example.shop.entity;


import com.example.shop.dtos.ProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int lprice;
    @Column(nullable = false)
    private int myprice;

    public Product(ProductRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
    }


//    public Product(ResultSet rs) throws SQLException {
//        this.id = rs.getLong(("id"));
//        this.image = rs.getString("image");
//        this.link = rs.getString("link");
//        this.lprice = rs.getInt("lprice");
//        this.myprice = rs.getInt("myprice");
//        this.title = rs.getString("title");
//    }
}
