package com.ratemusic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {

    @Email(message = "이메일은 형식이 잘못 되었습니다.")
    @NotBlank(message = "이메일은 필수 값 입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 값 입니다.")
    private String password;
}
