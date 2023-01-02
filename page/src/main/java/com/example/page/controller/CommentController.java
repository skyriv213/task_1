package com.example.page.controller;

import com.example.page.dto.comment.CommentRequest;
import com.example.page.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity createComment(@PathVariable Long postId, @RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        String message = commentService.createCommnet(postId, commentRequest, request);
        return new ResponseEntity<String>(message, HttpStatus.valueOf(200));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity deleteComment(@PathVariable Long postId, HttpServletRequest request) {
        String message = commentService.deleteComment(postId, request);
        return new ResponseEntity(message, HttpStatus.valueOf(200));
    }

    @PutMapping("/{postId}/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        String message = commentService.updateComment(postId, commentId, commentRequest, request);
        return new ResponseEntity(message, HttpStatus.valueOf(200));
    }

}
