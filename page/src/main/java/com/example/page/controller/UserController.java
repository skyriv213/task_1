package com.example.page.controller;


import com.example.page.dtos.user.LoginDto;
import com.example.page.dtos.user.UserRequestDto;
import com.example.page.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid UserRequestDto requestDto) {
        userService.signup(requestDto);
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
