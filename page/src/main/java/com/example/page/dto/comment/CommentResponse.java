package com.example.page.dto.comment;


import com.example.page.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponse {

    private String username;
    private String comment;

    public CommentResponse(Comment comment) {
        this.username = comment.getUser().getUsername();
        this.comment = comment.getComment();
    }
}
