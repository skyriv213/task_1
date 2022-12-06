package com.example.page.controller;

import com.example.page.dtos.PostRequestDtos;
import com.example.page.entiity.Post;
import com.example.page.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/api")
    public List<Post> getAllList() {
        return postService.getAllList();
    }

    @PostMapping("/api")
    public void postingSomething(@RequestBody PostRequestDtos postRequestDtos) {
        postService.createPosting(postRequestDtos);
    }

    @GetMapping("/api/{id}")
    public List<Post> getSomeList(@PathVariable Long id) {
        return postService.getSomeList(id);
    }

    @PutMapping("/api")
    public Long modifiedText(@RequestParam("id") Long id, @RequestBody PostRequestDtos postRequestDtos) {
        return postService.modifiedText(id,postRequestDtos);
    }


    @DeleteMapping("/api")
    public void deletePost(@RequestParam("id") Long id, @RequestParam("password") String password) {
        postService.deletePost(id,password);
    }
}
