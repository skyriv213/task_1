package com.example.page.dto.post;

import com.example.page.dto.user.UserResponseDto;
import com.example.page.entity.Post;
import com.example.page.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private UserResponseDto userResponseDto;
    private String title;
    private String content;

    public PostResponseDto(Post post) {
        this.userResponseDto = new UserResponseDto(post.getUser());
        this.title = post.getTitle();
        this.content = post.getContent();

    }
}
