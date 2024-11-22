package com.sparta.sch_user.dto;

import com.sparta.sch_user.entity.User;
import lombok.Getter;

// 새로운 사용자 생성 요청 데이터를 전달하는 DTO 클래스
@Getter
public class UserRequestDto {
    private final String writerName; // 사용자의 이름
    private final String email; // 사용자의 이메일
    private final String password; // 사용자의 비밀번호

    // 생성자: 이름, 이메일, 비밀번호를 초기화
    public UserRequestDto(String writerName, String email, String password) {
        this.writerName = writerName;
        this.email = email;
        this.password = password;
    }

    // DTO를 User 엔티티 객체로 변환하는 메서드
    public User toEntity() {
        return new User(
                this.writerName,
                this.email,
                this.password
        );
    }
}
