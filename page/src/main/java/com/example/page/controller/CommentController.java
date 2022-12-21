package com.example.page.controller;

import com.example.page.dto.comment.CommentRequestDto;
import com.example.page.entity.Comment;
import com.example.page.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create/{postId}")
    public ResponseEntity createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
        String commnet = commentService.createCommnet(postId, commentRequestDto, request);
        return new ResponseEntity<String>(commnet, HttpStatus.valueOf(200));
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity deleteComment(@PathVariable Long postId, HttpServletRequest request) {
        String deleteComment = commentService.deleteComment(postId, request);
        return new ResponseEntity(deleteComment, HttpStatus.valueOf(200));
    }

    @PutMapping("/update/{postId}/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest request) {
       String message = commentService.updateComment(postId, commentId, commentRequestDto, request);
        return new ResponseEntity(message, HttpStatus.valueOf(200));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> getIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
