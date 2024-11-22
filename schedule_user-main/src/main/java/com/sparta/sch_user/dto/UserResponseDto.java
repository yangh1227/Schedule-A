package com.sparta.sch_user.dto;

import com.sparta.sch_user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

// 사용자 조회 시 응답 데이터를 전달하는 DTO 클래스
@Getter
public class UserResponseDto {
    private final Long id; // 사용자 ID
    private final String writerName; // 사용자 이름
    private final String email; // 사용자 이메일
    private final LocalDateTime createdAt; // 계정 생성 날짜 및 시간
    private final LocalDateTime modifiedAt; // 계정 마지막 수정 날짜 및 시간

    // 생성자: 모든 필드를 초기화
    public UserResponseDto(Long id, String writerName, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.writerName = writerName;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // User 엔티티 객체를 DTO로 변환하는 정적 메서드
    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getWriterName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
