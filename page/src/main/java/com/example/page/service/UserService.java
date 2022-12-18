package com.example.page.service;

import com.example.page.dto.user.LoginDto;
import com.example.page.dto.user.UserRequestDto;
import com.example.page.entity.user.User;
import com.example.page.repository.UserRepository;
import com.example.page.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(UserRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다");
        }
        User user = new User(username, password);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public void login(LoginDto requestDto, HttpServletResponse response) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("존재하지않은 사용자입니다"));
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지않습니다");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER,jwtUtil.createToken(user.getUsername()));
    }

}
