package com.sparta.sch_user.service;

import com.sparta.sch_user.dto.ScheduleRequestDto;
import com.sparta.sch_user.dto.ScheduleResponseDto;
import com.sparta.sch_user.dto.ScheduleUpdateRequestDto;
import com.sparta.sch_user.entity.Schedule;
import com.sparta.sch_user.entity.User;
import com.sparta.sch_user.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// 스케줄 관련 비즈니스 로직을 처리하는 서비스 클래스
@Service // Spring에서 서비스 컴포넌트로 인식
@RequiredArgsConstructor // final 필드를 포함하는 생성자를 자동으로 생성
public class ScheduleService {

    private final ScheduleRepository scheduleRepository; // 스케줄 데이터를 관리하는 리포지토리
    private final UserService userService; // 사용자 관련 서비스, 사용자 유효성을 확인할 때 사용

    // 모든 스케줄 데이터를 조회하는 메서드
    public List<ScheduleResponseDto> findAll() {
        // 데이터베이스에서 모든 스케줄 엔티티를 조회
        List<Schedule> schedules = scheduleRepository.findAll();

        // Schedule 엔티티 리스트를 ScheduleResponseDto 리스트로 변환하여 반환
        return schedules
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    // 특정 ID에 해당하는 스케줄을 조회하는 메서드
    public ScheduleResponseDto findById(Long id) {
        // ID로 스케줄을 조회하고, DTO로 변환하여 반환
        return ScheduleResponseDto.toDto(findScheduleById(id));
    }

    // ID로 스케줄 엔티티를 조회하는 private 메서드 (내부에서만 사용)
    private Schedule findScheduleById(Long id) {
        // 스케줄이 존재하지 않을 경우 예외를 발생
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 ID 값 입니다."));
    }

    // 새로운 스케줄을 생성하는 메서드
    @Transactional // 트랜잭션 관리를 통해 데이터베이스 일관성 유지
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // 요청받은 사용자 ID로 사용자 엔티티를 조회
        User user = userService.findUserById(requestDto.getUserId());
        // 새로운 스케줄 객체 생성
        Schedule schedule = new Schedule(user, requestDto.getTitle(), requestDto.getDescription());

        // 스케줄을 데이터베이스에 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);
        // 저장된 스케줄을 DTO로 변환하여 반환
        return ScheduleResponseDto.toDto(savedSchedule);
    }

    // 특정 ID에 해당하는 스케줄을 삭제하는 메서드
    @Transactional
    public void deleteSchedule(Long id) {
        // 존재하는 스케줄인지 확인 (예외 처리를 위해)
        findScheduleById(id);
        // 스케줄 삭제
        scheduleRepository.deleteById(id);
    }

    // 특정 ID에 해당하는 스케줄을 업데이트하는 메서드
    @Transactional
    public ScheduleResponseDto update(Long id, ScheduleUpdateRequestDto scheduleRequestDto) {
        // ID로 스케줄 엔티티를 조회
        Schedule schedule = findScheduleById(id);
        // 스케줄 엔티티의 데이터를 업데이트
        schedule.update(scheduleRequestDto.getTitle(), scheduleRequestDto.getDescription());
        // 업데이트된 스케줄을 DTO로 변환하여 반환
        return ScheduleResponseDto.toDto(schedule);
    }
}
