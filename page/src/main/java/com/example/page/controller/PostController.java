package com.example.page.controller;

import com.example.page.dtos.PostDtos;
import com.example.page.entiity.Post;
import com.example.page.service.PostService;
import lombok.RequiredArgsConstructor;
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
    public void postingSomething(@RequestBody PostDtos postDtos) {

        postService.createPosting(postDtos);
    }

    @GetMapping("/api/{id}")
    public List<Post> getSomeList(@PathVariable Long id) {
        return postService.getSomeList(id);
    }

    @PatchMapping("/api")
    public String modifiedText(@RequestParam("id") Long id, @RequestBody PostDtos postDtos, @RequestParam("password") String password) {
        return postService.modifiedText(id, postDtos, password);
    }


    @DeleteMapping("/api")
    public String deletePost(@RequestParam("id") Long id, @RequestParam("password") String password) {
        return postService.deletePost(id,password);

    }
}
