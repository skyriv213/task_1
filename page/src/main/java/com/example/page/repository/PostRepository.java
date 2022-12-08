package com.example.page.repository;

import com.example.page.entiity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByOrderByModifiedAtDesc();

    List<Post> findAllById(Long id);

    Post findByIdAndPassword(Long id, String pw);
}
