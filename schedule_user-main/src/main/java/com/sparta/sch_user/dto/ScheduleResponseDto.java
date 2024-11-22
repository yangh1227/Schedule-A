package com.sparta.sch_user.dto;

import com.sparta.sch_user.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

// 스케줄 조회 시 응답 데이터를 전달하는 DTO 클래스
@Getter
public class ScheduleResponseDto {
    private final Long id; // 스케줄 ID
    private final Long userId; // 스케줄 작성자의 사용자 ID
    private final String title; // 스케줄 제목
    private final String description; // 스케줄 내용
    private final LocalDateTime createdAt; // 생성 날짜 및 시간
    private final LocalDateTime modifiedAt; // 마지막 수정 날짜 및 시간

    // 생성자: 모든 필드를 초기화
    public ScheduleResponseDto(Long id, Long userId, String title, String description, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // Schedule 엔티티 객체를 DTO로 변환하는 정적 메서드
    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getUser().getId(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
