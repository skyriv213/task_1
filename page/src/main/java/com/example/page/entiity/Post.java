package com.example.page.entiity;


import com.example.page.dtos.PostRequestDtos;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String text;


    public Post(PostRequestDtos postRequestDtos) {
        this.name = postRequestDtos.getName();
        this.password = postRequestDtos.getPassword();
        this.text = postRequestDtos.getText();

    }

    public void update(PostRequestDtos postRequestDtos) {
        this.text = postRequestDtos.getText();
    }
}
