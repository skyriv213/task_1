package com.example.page.dto;

import com.example.page.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@Getter
@Builder
public class PostCommentDto {

    private String postname;
    private String postConetnets;
    private List<Comment> commentList;

    public 
}
