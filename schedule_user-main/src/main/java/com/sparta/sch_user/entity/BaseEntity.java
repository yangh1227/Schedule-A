package com.sparta.sch_user.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 모든 엔티티에서 공통으로 사용될 필드와 기능을 정의한 추상 클래스
@Getter
@MappedSuperclass // 이 클래스의 필드들을 상속받는 엔티티의 필드로 포함되도록 지정
@EntityListeners(AuditingEntityListener.class) // 엔티티 변경을 감지하여 자동으로 날짜를 업데이트
public abstract class BaseEntity {

    // 엔티티가 생성된 날짜 및 시간을 저장
    @CreatedDate // 데이터가 처음 저장될 때 날짜를 자동으로 생성
    private LocalDateTime createdAt;

    // 엔티티가 마지막으로 수정된 날짜 및 시간을 저장
    @LastModifiedDate // 데이터가 수정될 때 날짜를 자동으로 업데이트
    private LocalDateTime modifiedAt;
}
