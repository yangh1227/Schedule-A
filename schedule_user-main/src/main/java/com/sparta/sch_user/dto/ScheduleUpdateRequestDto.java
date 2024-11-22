package com.sparta.sch_user.dto;

import lombok.Getter;

// 스케줄 업데이트 요청 데이터를 전달하는 DTO 클래스
@Getter
public class ScheduleUpdateRequestDto {
    private final String title; // 수정할 스케줄 제목
    private final String description; // 수정할 스케줄 내용

    // 생성자: 제목과 내용을 초기화
    public ScheduleUpdateRequestDto(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
