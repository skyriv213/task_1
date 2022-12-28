package com.example.page.entity;

import com.example.page.dto.comment.CommentRequestDto;
import com.example.page.entity.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postId",nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    private String comment;

    public Comment(User user, Post post, String comment) {
        this.post = post;
        this.user = user;
        this.comment = comment;
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
    }

}
