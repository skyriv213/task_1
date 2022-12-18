package com.example.page.dto.user;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    @Pattern(regexp = "^[0-9a-zA-Z]*$",message = "아이디 형식에 맞지 않습니다. 아이디는 4 ~ 10 자리의 영문(소문자), 숫자로 이루어져야합니다")
    @Size(min = 4, max = 10)
    private String username;

    @Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "비밀번호 형식에 맞지않습니다. 비밀번호는 8 ~ 15자리 영문(대,소문자),숫자로 이루어져야합니다")
    @Size(min = 8,max = 15)
    private String password;
}
