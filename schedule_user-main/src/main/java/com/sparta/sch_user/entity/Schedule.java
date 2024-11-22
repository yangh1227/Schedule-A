package com.sparta.sch_user.entity;

import jakarta.persistence.*;
import lombok.Getter;

// 스케줄 정보를 저장하는 엔티티 클래스
@Entity // JPA가 관리하는 엔티티임을 나타냄
@Getter // 필드에 대한 Getter 메서드를 자동 생성
public class Schedule extends BaseEntity { // BaseEntity를 상속받아 생성/수정 시간을 관리

    @Id // 이 필드가 기본 키(Primary Key)임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 값을 데이터베이스에서 자동 생성
    private Long id;

    private String title; // 스케줄 제목
    private String description; // 스케줄 내용

    @ManyToOne // 다대일 관계를 나타냄: 여러 스케줄은 한 사용자를 가질 수 있음
    @JoinColumn(name = "user_id") // 외래 키 컬럼명 지정
    private User user; // 스케줄과 연결된 사용자

    // 사용자와 스케줄 제목 및 내용을 초기화하는 생성자
    public Schedule(User user, String title, String description) {
        this.user = user;
        this.title = title;
        this.description = description;
    }

    // 기본 생성자: JPA가 엔티티를 생성할 때 사용 (필수)
    public Schedule() {}

    // 스케줄의 제목과 내용을 수정하는 메서드
    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
