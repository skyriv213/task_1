package com.example.page.dtos;


import com.example.page.entiity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDtos {
    private String name;
    private String password;
    private String text;

    public PostDtos(Post post) {
        name = post.getName();
        password = post.getPassword();
        text =  post.getText();

    }
}
