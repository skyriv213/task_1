package com.example.page.dto.post;

import com.example.page.dto.user.UserResponseDto;
import com.example.page.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponse {
    private UserResponseDto userResponseDto;
    private String title;
    private String content;

    public PostResponse(Post post) {
        this.userResponseDto = new UserResponseDto(post.getUser());
        this.title = post.getTitle();
        this.content = post.getContent();

    }
}
