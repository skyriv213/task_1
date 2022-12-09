package com.example.page.entiity;


import com.example.page.dtos.PostDtos;

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


    public Post(PostDtos postDtos) {
        this.name = postDtos.getName();
        this.password = postDtos.getPassword();
        this.text = postDtos.getText();

    }


    public void update(PostDtos postRequestDtos) {
        this.text = postRequestDtos.getText();
    }
}
