package com.sparta.sch_user.dto;

import lombok.Getter;

// 새로운 스케줄 생성 요청 데이터를 전달하는 DTO 클래스
@Getter
public class ScheduleRequestDto {
    private final Long userId; // 스케줄 작성자의 사용자 ID
    private final String title; // 생성할 스케줄 제목
    private final String description; // 생성할 스케줄 내용

    // 생성자: 사용자 ID, 제목, 내용을 초기화
    public ScheduleRequestDto(Long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }
}
