package com.example.page.service;

import com.example.page.dto.comment.CommentRequest;
import com.example.page.dto.comment.CommentResponse;
import com.example.page.entity.Comment;
import com.example.page.entity.Post;
import com.example.page.entity.user.User;
import com.example.page.repository.CommentRepository;
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

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public String createCommnet(Long postId, CommentRequest commentRequest, HttpServletRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 postID는 없는 ID 입니다"));
        String token = jwtUtil.resolveToken(request);
        Claims claim = createClaim(token);
        String username = claim.getSubject();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("해당 유저는 없는 유저입니다"));
        Comment comment = new Comment(user, post, commentRequest.getComment());
        commentRepository.save(comment);
        return user.getUsername() + "의 댓글이 저장되었습니다";
    }

    //    public ResponseEntity getCommentList() {
//        List<Comment> allByOrderByModifiedAtDesc = commentRepository.findAllByOrderByModifiedAtDesc();
//        return new ResponseEntity
//    }
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
    @Transactional
    public String deleteComment(Long postId, HttpServletRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 postID는 없는 ID 입니다"));
        String token = jwtUtil.resolveToken(request);
        Claims claim = createClaim(token);
        User user = userRepository.findByUsername(claim.getSubject()).orElseThrow(() -> new IllegalArgumentException("등록되지않은 유저입니다"));
        Comment comment = commentRepository.findByUserAndPost(user, post);
        commentRepository.delete(comment);
        return user.getUsername() + "의 댓글이 지워졌습니다";
    }

    @Transactional
    public String updateComment(Long postId, Long commentId, CommentRequest commentRequest, HttpServletRequest request) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 postID는 없는 ID 입니다"));
        String token = jwtUtil.resolveToken(request);
        Claims claim = createClaim(token);
        User user = userRepository.findByUsername(claim.getSubject()).orElseThrow(() -> new IllegalArgumentException("등록되지않은 유저입니다"));

        Comment comment = commentRepository.findByUserAndPost(user, post);
        comment.update(commentRequest);
        return comment.getUser().getUsername() + "의 댓글이 수정되었습니다. " +"수정시간 : " +comment.getModifiedAt();
    }


    public List<CommentResponse> getSomeComment(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("등록되지않은 post입니다"));
        List<Comment> allByComment = post.getComments();
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : allByComment) {
            commentResponses.add(new CommentResponse(comment));
        }
        return commentResponses;
    }
}
