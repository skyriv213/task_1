package com.example.page.dto.comment;


import com.example.page.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private String username;
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.username = comment.getUser().getUsername();
        this.comment = comment.getComment();
    }
}
