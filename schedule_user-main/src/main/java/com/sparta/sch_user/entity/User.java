package com.sparta.sch_user.entity;

import jakarta.persistence.*;
import lombok.Getter;

// 사용자 정보를 저장하는 엔티티 클래스
@Entity // JPA가 관리하는 엔티티임을 나타냄
@Getter // 필드에 대한 Getter 메서드를 자동 생성
public class User extends BaseEntity { // BaseEntity를 상속받아 생성/수정 시간을 자동 관리

    @Id // 이 필드가 기본 키(Primary Key)임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 값을 데이터베이스에서 자동 생성
    private Long id;

    private String writerName; // 사용자의 이름
    private String email; // 사용자의 이메일 주소
    private String password; // 사용자의 비밀번호

    // 기본 생성자: JPA가 엔티티를 생성할 때 사용 (필수)
    public User() {}

    // 사용자 정보를 초기화하는 생성자
    public User(String writerName, String email, String password) {
        this.writerName = writerName;
        this.email = email;
        this.password = password;
    }
}
