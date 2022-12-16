package com.example.page.dtos.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserRequestDto {

    @Pattern(regexp = "^[0-9a-z]*$",message = "아이디 형식에 맞지 않습니다. 아이디는 4 ~ 10 자리의 영문(소문자), 숫자로 이루어져야합니다")
    @Size(min = 4, max = 10)
    private String username;

    @Pattern(regexp = "[0-9a-zA-Z]*$", message = "비밀번호 형식에 맞지않습니다. 비밀번호는 8 ~ 15자리 영문(대,소문자),숫자로 이루어져야합니다")
    @Size(min = 8,max = 15)
    private String password;
}
