package com.example.page.controller;

import com.example.page.dto.user.LoginDto;
import com.example.page.dto.user.UserRequestDto;
import com.example.page.security.UserDetailsImpl;
import com.example.page.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/users0")
    public ResponseEntity signup(@RequestBody @Valid UserRequestDto requestDto) {
        String signup = userService.signup(requestDto);
        return new ResponseEntity<String>(signup, HttpStatus.valueOf(200));
    }

    @ResponseBody
    @PostMapping("/logins")
    public void login(@AuthenticationPrincipal UserDetails userDetails) {
//        userService.login(userDetails);
        System.out.println("*********************************************************");
        System.out.println("UserController.login");
        System.out.println("userDetails.getUsername() = " + userDetails.getUsername());
        System.out.println("*********************************************************");

    }
}
