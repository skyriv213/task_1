package com.example.page.service;

import com.example.page.dtos.PostRequestDtos;
import com.example.page.entiity.Post;
import com.example.page.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    @Transactional()
    public List<Post> getAllList() {
        List<Post> allList = postRepository.findAllByOrderByModifiedAt();
        return allList;
    }

    @Transactional
    public void createPosting(PostRequestDtos postRequestDtos) {
        Post post = new Post(postRequestDtos);
        postRepository.save(post);

    }

    @Transactional
    public List<Post> getSomeList(Long id) {
        List<Post> byId = postRepository.findAllById(id);
        return byId;
    }


    @Transactional
    public Long modifiedText(Long id, PostRequestDtos postRequestDtos) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("등록되지않은 아이디입니다"));
        post.update(postRequestDtos);
//        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public void deletePost(Long id, String pw) {
        Post post = postRepository.findByIdAndPassword(id, pw);

        postRepository.delete(post);


    }

}
