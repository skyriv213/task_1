package com.example.page.dto;

import com.example.page.dto.comment.CommentResponseDto;
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
    private List<CommentResponseDto> commentList;

    public PostCommentDto(PostResponse postResponse, List<CommentResponseDto> commentResponseDtos) {
        this.postTitle = postResponse.getTitle();
        this.postConetnets = postResponse.getContent();
        this.commentList = Collections.unmodifiableList(commentResponseDtos);
    }
}
