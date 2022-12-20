package com.example.page.dto.user;

import com.example.page.entity.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String username;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
    }
}
