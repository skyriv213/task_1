package com.example.page.service;

import com.example.page.entity.Comment;
import com.example.page.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private CommentRepository commentRepository;

//    public ResponseEntity getCommentList() {
//        List<Comment> allByOrderByModifiedAtDesc = commentRepository.findAllByOrderByModifiedAtDesc();
//        return new ResponseEntity
//    }
}
