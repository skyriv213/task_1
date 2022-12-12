package com.example.page.service;

import com.example.page.dtos.PostDtos;
import com.example.page.entiity.Post;
import com.example.page.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    @Transactional()
    public List<Post> getAllList() {
        List<Post> allList = postRepository.findAllByOrderByModifiedAtDesc();
        return allList;
    }

    @Transactional
    public void createPosting(PostDtos postRequestDtos) {
        Post post = new Post(postRequestDtos);
        postRepository.save(post);

    }


    @Transactional
    public List<Post> getSomeList(Long id) {
        List<Post> byId = postRepository.findAllById(id);
        return byId;
    }


    @Transactional
    public String modifiedText(Long id, PostDtos postRequestDtos, String password) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("등록되지않은 아이디입니다"));
        if (post.getPassword().equals(password)) {
            post.update(postRequestDtos);
            PostDtos postDtos = new PostDtos(post);
            return postDtos.getName() + "의 글이 수정되었습니다";
        }
        return "잘못된 접근입니다";
//        postRepository.save(post);
    }

    @Transactional
    public String deletePost(Long id, String pw) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 id는 저장된 id가 아닙니다"));
        if (post.getPassword().equals(pw)) {
            postRepository.delete(post);
            return id + "값이 삭제되었습니다";
        }
        return "올바른 Password가 아닙니다";



    }

}
