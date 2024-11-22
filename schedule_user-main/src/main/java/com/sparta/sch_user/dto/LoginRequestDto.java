package com.sparta.sch_user.dto;

import lombok.Getter;

// 사용자가 로그인할 때 요청 데이터를 전달하는 DTO (Data Transfer Object) 클래스
@Getter
public class LoginRequestDto {
    private final String email; // 사용자의 이메일
    private final String password; // 사용자의 비밀번호

    // 생성자: 이메일과 비밀번호를 초기화
    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
