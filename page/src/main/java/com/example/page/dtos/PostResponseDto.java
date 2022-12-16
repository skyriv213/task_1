package com.example.page.dtos;


import com.example.page.entiity.Post;
import com.example.page.entiity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDtos {
    private User user;
    private String title;
    private String Context;


}
