package com.example.page.service;

import com.example.page.dto.post.ChangeContext;
import com.example.page.dto.post.PostRequest;
import com.example.page.dto.post.PostResponse;
import com.example.page.entity.Post;
import com.example.page.entity.user.Grade;
import com.example.page.entity.user.User;
import com.example.page.repository.PostRepository;
import com.example.page.repository.UserRepository;
import com.example.page.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    // 전체 게시글 조회
    @Transactional()
    public List<PostResponse> getAllPost() {
        List<Post> allList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponse> postResponses = new ArrayList<>();
        for (Post post : allList) {
            postResponses.add(new PostResponse(post));
        }
        return postResponses;
    }


    //게시글 생성
    @Transactional
    public String createPosting(PostRequest postRequestDtos, HttpServletRequest request) {
        // Request에서 Token 가져오기
        Claims claims = createClaim(jwtUtil.resolveToken(request));

        String username = claims.getSubject();
        User user = findUser(username);
        // 토큰이 있는 경우에만
        Post post = new Post(user, postRequestDtos);
        postRepository.save(post);
        PostResponse postResponse = new PostResponse(post);
        return postResponse.getUserResponseDto().getUsername();
    }


    // 특정 게시글 조회
    @Transactional
    public PostResponse getSomeList(Long id, HttpServletRequest request) {
        /**
         * return responseEntity -> dto
         * dto 내부에 post 필드, Comment - list타입
         * return 으로 dto 반환
         * 한번에 response로 내려주면 대용량으로 메모리 잡아먹게 됨
         * 원하는 필드만 response로 내려주기 -> dto 객체 활용
         * entity -> dto로 변환 후 반환하기 ★★★★★
         * ★★★ 키워드 : contentLocation ★★★
         */

        String token = jwtUtil.resolveToken(request);
        Claims claims = createClaim(token);
        User user = findUser(claims.getSubject());
        if (user.getGrade().equals(Grade.ADMIN)) {
            Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 아이디 입니다"));
            PostResponse postResponse = new PostResponse(post);
            return postResponse;
        } else {
            boolean check = userRepository.existsByUsername(user.getUsername());
            if (check) {
                Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글은 존재하지않습니다"));
                PostResponse postResponse = new PostResponse(post);
                return postResponse;
            } else {
                throw new IllegalArgumentException("해당 유저는 이 글을 조회할 권한이 없습니다");
            }

        }
    }

    // 특정 게시글 수정 미완

    @Transactional
    public String modifiedContent(Long id, ChangeContext changeContext, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = createClaim(token);
        User user = findUser(claims.getSubject());


        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("등록되지않은 아이디입니다"));
        if (user.getGrade().equals(Grade.ADMIN)) {
            post.update(changeContext);
            return post.getUser().getUsername() + "의 " + id + "번째 글이 수정되었습니다";
        } else {
            if (post.getUser().equals(user)) {
                post.update(changeContext);
                return post.getUser().getUsername() + "의 " + id + "번째 글이 수정되었습니다";
            } else {
                throw new IllegalArgumentException("잘못된 접근입니다");
            }

        }
    }
    // 특정 게시글 삭제

    @Transactional
    public String deletePost(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = createClaim(token);
        User user = findUser(claims.getSubject());

        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글은 존재하지않습니다"));
        if (user.getGrade().equals(Grade.ADMIN)) {
            postRepository.delete(post);
            return user.getUsername() + "의 글이 성공적으로 지워졌습니다";
        } else {
            if (post.getUser().equals(user)) {
                postRepository.delete(post);
                return user.getUsername() + "의 글이 성공적으로 지워졌습니다";
            } else {
                throw new IllegalArgumentException("잘못된 유저의 입력입니다");
            }
        }

    }

    private Claims createClaim(String token) {
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 추가 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
                return claims;
            } else {
                throw new IllegalArgumentException("Token Error");
            }
        } else {
            throw new NullPointerException("Token Error");
        }
    }

    private User findUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("없는 형식의 아이디 입니다")
        );
    }
}
