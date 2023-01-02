package com.example.page.dto;

import com.example.page.dto.comment.CommentResponse;
import com.example.page.dto.post.PostResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostCommentDto {

    private String postTitle;
    private String postConetnets;
    private List<CommentResponse> commentList;

    public PostCommentDto(PostResponse postResponse, List<CommentResponse> commentResponses) {
        this.postTitle = postResponse.getTitle();
        this.postConetnets = postResponse.getContent();
        this.commentList = Collections.unmodifiableList(commentResponses);
    }
}
