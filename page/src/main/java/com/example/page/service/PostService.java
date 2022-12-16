package com.example.page.service;

import com.example.page.dtos.ChangeContext;
import com.example.page.dtos.PostRequestDto;
import com.example.page.dtos.PostResponseDto;
import com.example.page.entiity.Post;
import com.example.page.entiity.user.User;
import com.example.page.repository.PostRepository;
import com.example.page.repository.UserRepository;
import com.example.page.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    // 전체 게시글 조회
    @Transactional()
    public List<Post> getAllList() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    //게시글 생성
    @Transactional
    public ResponseEntity createPosting(PostRequestDto postRequestDtos, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );
            // 토큰이 있는 경우에만
            Post post = new Post(user, postRequestDtos);
            postRepository.save(post);

            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("잘못된 유저의 접근입니다", HttpStatus.BAD_REQUEST);
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


    // 특정 게시글 조회
    @Transactional
    public ResponseEntity getSomeList(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = createClaim(token);
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("없는 형식의 아이디 입니다")
        );
        return new ResponseEntity<>(postRepository.findByUserAndId(user, id), HttpStatus.OK);
    }


    // 특정 게시글 수정 미완
    @Transactional
    public ResponseEntity modifiedContent(Long id, ChangeContext changeContext, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = createClaim(token);
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("없는 형식의 아이디 입니다")
        );
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("등록되지않은 아이디입니다"));
        if (post.getUser().equals(user)) {
            post.update(changeContext);
            return new ResponseEntity<>(user.getUsername() + "의 글이 업데이트 완료", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("잘못된 유저의 접근입니다", HttpStatus.BAD_REQUEST);
        }
    }

    // 특정 게시글 삭제
    @Transactional
    public ResponseEntity deletePost(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims = createClaim(token);
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("없는 형식의 아이디 입니다")
        );
        Post post = postRepository.findByUserAndId(user, id);
        if (post.getUser().equals(user)) {
            postRepository.delete(post);
            return new ResponseEntity(user.getUsername() + "의 글이 성공적으로 지워졌습니다", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("잘못된 유저의 접근입니다", HttpStatus.BAD_REQUEST);
        }

    }
}

