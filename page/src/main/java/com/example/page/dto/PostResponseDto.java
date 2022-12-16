package com.example.page.dto;

import com.example.page.entity.Post;
import com.example.page.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private User user;
    private String title;
    private String content;

    public PostResponseDto(Post post) {
        this.user = post.getUser();
        this.title = post.getTitle();
        this.content = post.getContent();

    }
}
