package com.example.page.repository;

import com.example.page.entity.Comment;
import com.example.page.entity.Post;
import com.example.page.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtDesc();
    Comment findByUserAndPost(User user, Post post);

    Comment findByPost(Post post);

    List<Comment> findAllByPost(Post post);
}
