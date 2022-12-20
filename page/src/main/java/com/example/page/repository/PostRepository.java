package com.example.page.repository;

import com.example.page.entity.Post;
import com.example.page.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByOrderByModifiedAtDesc();

    Optional<Post> findById(Long id);
    Post findByUserAndId(User user, Long id);
//    List<Post> findAllById(Long id);


}
