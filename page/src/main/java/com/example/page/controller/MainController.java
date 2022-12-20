package com.example.page.controller;

import com.example.page.dto.PostCommentDto;
import com.example.page.dto.comment.CommentResponseDto;
import com.example.page.dto.post.ChangeContext;
import com.example.page.dto.post.PostRequestDto;
import com.example.page.dto.post.PostResponseDto;
import com.example.page.service.CommentService;
import com.example.page.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class MainController {

    private final PostService postService;
    private final CommentService commentService;
    // 전체 게시글 조회
    @GetMapping("/post")
    public ResponseEntity getAllPost() {
        List<PostResponseDto> postResponseDtos = postService.getAllPost();
        HttpHeaders headers = new HttpHeaders();
        headers.set("succeess", String.valueOf(HttpStatus.valueOf(200)));

        return new ResponseEntity<List<PostResponseDto>>(postResponseDtos, headers, HttpStatus.valueOf(200));
    }

    // 게시글 생성
    @PostMapping("/post")
    public ResponseEntity  createPosting(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        String id = postService.createPosting(postRequestDto, request);
        HttpHeaders headers = new HttpHeaders();
        headers.set(id, String.valueOf(HttpStatus.valueOf(200)));
        return new ResponseEntity(id, headers, HttpStatus.valueOf(200));
    }

    // 특정 게시글 조회
    @GetMapping("/post/{id}")
    public ResponseEntity getSpecific(@PathVariable Long id, HttpServletRequest request) {
        PostResponseDto somePost = postService.getSomeList(id, request);
        List<CommentResponseDto> commentResponseDto =  commentService.getSomeComment(id);
        PostCommentDto postCommentDto = new PostCommentDto(somePost, commentResponseDto);
        return new ResponseEntity<PostCommentDto>(postCommentDto, HttpStatus.valueOf(200));

    }

    //    특정 게시글 수정
    @PatchMapping("/post/{id}")
    public ResponseEntity modifiedContent(@PathVariable Long id, @RequestBody ChangeContext changeContext, HttpServletRequest request) {
        String s = postService.modifiedContent(id, changeContext, request);
        return new ResponseEntity(s, HttpStatus.valueOf(200));
    }


    // 특정 개시글 삭제
    @DeleteMapping("/post")
    public ResponseEntity deletePost(@RequestParam("id") Long id, HttpServletRequest request) {
        String userNameMassage = postService.deletePost(id, request);
        return new ResponseEntity(userNameMassage, HttpStatus.valueOf(200));

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> getIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
