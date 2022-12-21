package com.example.page.controller;

import com.example.page.dto.user.LoginDto;
import com.example.page.dto.user.UserRequestDto;
import com.example.page.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid UserRequestDto requestDto) {
        String signup = userService.signup(requestDto);
        return new ResponseEntity<String>(signup, HttpStatus.valueOf(200));
    }

    @ResponseBody
    @PostMapping("/login")
    public void login(@RequestBody LoginDto requestDto, HttpServletResponse response) {
        userService.login(requestDto, response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> getIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }



}
