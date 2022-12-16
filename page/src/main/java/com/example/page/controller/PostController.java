package com.example.page.controller;

import com.example.page.dto.ChangeContext;
import com.example.page.dto.PostRequestDto;
import com.example.page.entity.Post;
import com.example.page.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class PostController {

    private final PostService postService;

    // 전체 게시글 조회
    @GetMapping("/post")
    public List<Post> getAllList() {
        return postService.getAllList();
    }

    // 게시글 생성
    @PostMapping("/post")
    public ResponseEntity postingSomething(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {

        return postService.createPosting(postRequestDto, request);
    }

    // 특정 게시글 조회
    @GetMapping("/post/{id}")
    public ResponseEntity getSomeList(@PathVariable Long id, HttpServletRequest request) {
        return postService.getSomeList(id,request);
    }

    //    특정 게시글 수정
    @PatchMapping("/post/{id}")
    public ResponseEntity modifiedContent(@PathVariable Long id, @RequestBody ChangeContext changeContext, HttpServletRequest request) {
        return postService.modifiedContent(id,changeContext,request);
    }


    // 특정 개시글 삭제
    @DeleteMapping("/post")
    public ResponseEntity deletePost(@RequestParam("id") Long id, HttpServletRequest request) {
        return postService.deletePost(id,request);

    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> getIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
