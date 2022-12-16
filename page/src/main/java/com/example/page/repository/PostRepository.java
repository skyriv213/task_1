package com.example.page.repository;

import com.example.page.entiity.Post;
import com.example.page.entiity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByOrderByModifiedAtDesc();

    Post findByUserAndId(User user, Long id);
//    List<Post> findAllById(Long id);


}
