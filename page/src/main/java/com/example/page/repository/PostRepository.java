package com.example.page.repository;

import com.example.page.entity.Post;
import com.example.page.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByOrderByModifiedAtDesc();

    Post findByUserAndId(User user, Long id);
//    List<Post> findAllById(Long id);


}
